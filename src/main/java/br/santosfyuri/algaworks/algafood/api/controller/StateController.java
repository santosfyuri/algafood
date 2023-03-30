package br.santosfyuri.algaworks.algafood.api.controller;

import br.santosfyuri.algaworks.algafood.api.assembler.AssemblerParameters;
import br.santosfyuri.algaworks.algafood.api.assembler.BasicAssembler;
import br.santosfyuri.algaworks.algafood.api.assembler.BasicDisassembler;
import br.santosfyuri.algaworks.algafood.api.openapi.controller.StateControllerOpenApi;
import br.santosfyuri.algaworks.algafood.api.representation.request.StateRequest;
import br.santosfyuri.algaworks.algafood.api.representation.response.StateResponse;
import br.santosfyuri.algaworks.algafood.domain.model.State;
import br.santosfyuri.algaworks.algafood.domain.repository.StateRepository;
import br.santosfyuri.algaworks.algafood.domain.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static br.santosfyuri.algaworks.algafood.api.helpers.AlgaLinks.linkToState;

@RestController
@RequestMapping("/states")
public class StateController implements StateControllerOpenApi {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateService stateService;

    @Autowired
    private BasicAssembler assembler;

    @Autowired
    private BasicDisassembler disassembler;

    @GetMapping
    public CollectionModel<StateResponse> list() {
        return assembler.<State, StateResponse>get(getParameters())
                .toCollectionModel(stateRepository.findAll());
    }

    @GetMapping(path = "{id}")
    public StateResponse find(@PathVariable Long id) {
        return assembler.<State, StateResponse>get(getParameters())
                .toModel(stateService.findOrNull(id));
    }

    @PostMapping
    public StateResponse save(@RequestBody @Valid StateRequest input) {
        State state = disassembler.<State, StateRequest>get(State.class)
                .representationToEntity(input);
        return assembler.<State, StateResponse>get(getParameters())
                .toModel(stateService.save(state));
    }

    @PutMapping(path = "{id}")
    public StateResponse update(@PathVariable Long id,
                                @RequestBody @Valid StateRequest input) {
        State currentState = stateService.findOrNull(id);
        disassembler.<State, StateRequest>get(State.class).copyToEntity(input, currentState);
        return assembler.<State, StateResponse>get(getParameters())
                .toModel(stateService.save(currentState));
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        stateService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private AssemblerParameters<StateResponse> getParameters() {
        return new AssemblerParameters<>(StateResponse.class, this.getClass(), discover -> {
            discover.add(linkToState());
            discover.add(linkToState(discover.getId()));
        });
    }
}
