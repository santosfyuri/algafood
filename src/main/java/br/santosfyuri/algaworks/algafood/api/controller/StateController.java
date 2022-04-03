package br.santosfyuri.algaworks.algafood.api.controller;

import br.santosfyuri.algaworks.algafood.domain.exception.EntityNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.model.State;
import br.santosfyuri.algaworks.algafood.domain.repository.StateRepository;
import br.santosfyuri.algaworks.algafood.domain.service.StateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateService stateService;

    @GetMapping
    public List<State> list() {
        return stateRepository.findAll();
    }

    @GetMapping(path = "{id}")
    public State find(@PathVariable Long id) {
        return stateService.findOrNull(id);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid State state) {
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
    public State update(@PathVariable Long id,
                        @RequestBody @Valid State state) {
        State currentState = stateService.findOrNull(id);
        BeanUtils.copyProperties(state, currentState, "id");
        return stateService.save(currentState);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Long id) {
        stateService.delete(id);
    }
}
