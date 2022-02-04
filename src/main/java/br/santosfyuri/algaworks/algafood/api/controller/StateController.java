package br.santosfyuri.algaworks.algafood.api.controller;

import br.santosfyuri.algaworks.algafood.domain.exception.EntityInUseException;
import br.santosfyuri.algaworks.algafood.domain.exception.EntityNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.model.State;
import br.santosfyuri.algaworks.algafood.domain.repository.StateRepository;
import br.santosfyuri.algaworks.algafood.domain.service.StateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateService stateService;

    @GetMapping
    public List<State> list() {
        return stateRepository.list();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<State> find(@PathVariable Long id) {
        State state = stateRepository.find(id);
        if (Objects.nonNull(state)) {
            return ResponseEntity.ok(state);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody State state) {
        try {
            state = stateService.save(state);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(state);
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.badRequest()
                    .body(exception.getMessage());
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody State state) {
        try {
            State currentState = stateRepository.find(id);
            if (Objects.nonNull(currentState)) {
                BeanUtils.copyProperties(state, currentState, "id");
                currentState = stateService.save(currentState);
                return ResponseEntity.ok(currentState);
            }
            return ResponseEntity.notFound().build();
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.badRequest()
                    .body(exception.getMessage());
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<State> delete(@PathVariable(value = "id") Long id) {
        try {
            stateService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.notFound().build();
        } catch (EntityInUseException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
