package br.santosfyuri.algaworks.algafood.api.controller;

import br.santosfyuri.algaworks.algafood.api.assembler.BasicAssembler;
import br.santosfyuri.algaworks.algafood.api.assembler.BasicDisassembler;
import br.santosfyuri.algaworks.algafood.api.openapi.controller.KitchenControllerOpenApi;
import br.santosfyuri.algaworks.algafood.api.representation.request.KitchenRequest;
import br.santosfyuri.algaworks.algafood.api.representation.response.KitchenResponse;
import br.santosfyuri.algaworks.algafood.domain.model.Kitchen;
import br.santosfyuri.algaworks.algafood.domain.repository.KitchenRepository;
import br.santosfyuri.algaworks.algafood.domain.service.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/kitchens")
public class KitchenController implements KitchenControllerOpenApi {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private KitchenService kitchenService;

    @Autowired
    private BasicAssembler assembler;

    @Autowired
    private BasicDisassembler disassembler;

    @GetMapping
    public Page<KitchenResponse> list(Pageable pageable) {
        Page<Kitchen> kitchensPage = kitchenRepository.findAll(pageable);
        List<KitchenResponse> kitchens = assembler.<Kitchen, KitchenResponse>get(KitchenResponse.class)
                .entityToRepresentation(kitchensPage.getContent());
        return new PageImpl<>(kitchens, pageable, kitchensPage.getTotalElements());
    }

    @GetMapping(path = "{id}")
    public KitchenResponse find(@PathVariable Long id) {
        return assembler.<Kitchen, KitchenResponse>get(KitchenResponse.class)
                .entityToRepresentation(kitchenService.findOrNull(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public KitchenResponse save(@RequestBody @Valid KitchenRequest kitchenRequest) {
        Kitchen kitchen = disassembler.<Kitchen, KitchenRequest>get(Kitchen.class).representationToEntity(kitchenRequest);
        return assembler.<Kitchen, KitchenResponse>get(KitchenResponse.class)
                .entityToRepresentation(kitchenService.save(kitchen));
    }

    @PutMapping(path = "{id}")
    public KitchenResponse update(@PathVariable Long id,
                                  @RequestBody @Valid KitchenRequest kitchenRequest) {
        Kitchen currentKitchen = kitchenService.findOrNull(id);
        disassembler.<Kitchen, KitchenRequest>get(Kitchen.class).copyToEntity(kitchenRequest, currentKitchen);
        return assembler.<Kitchen, KitchenResponse>get(KitchenResponse.class)
                .entityToRepresentation(kitchenService.save(currentKitchen));
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Long id) {
        kitchenService.delete(id);
    }
}
