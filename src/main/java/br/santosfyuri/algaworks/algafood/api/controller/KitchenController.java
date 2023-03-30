package br.santosfyuri.algaworks.algafood.api.controller;

import br.santosfyuri.algaworks.algafood.api.assembler.AssemblerParameters;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static br.santosfyuri.algaworks.algafood.api.helpers.AlgaLinks.linkToKitchen;

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

    @Autowired
    private PagedResourcesAssembler<Kitchen> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<KitchenResponse> list(Pageable pageable) {
        Page<Kitchen> kitchensPage = kitchenRepository.findAll(pageable);
        return pagedResourcesAssembler.toModel(kitchensPage, assembler.get(getParameters()));
    }

    @GetMapping(path = "{id}")
    public KitchenResponse find(@PathVariable Long id) {
        return assembler.<Kitchen, KitchenResponse>get(getParameters())
                .toModel(kitchenService.findOrNull(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public KitchenResponse save(@RequestBody @Valid KitchenRequest kitchenRequest) {
        Kitchen kitchen = disassembler.<Kitchen, KitchenRequest>get(Kitchen.class).representationToEntity(kitchenRequest);
        return assembler.<Kitchen, KitchenResponse>get(getParameters())
                .toModel(kitchenService.save(kitchen));
    }

    @PutMapping(path = "{id}")
    public KitchenResponse update(@PathVariable Long id,
                                  @RequestBody @Valid KitchenRequest kitchenRequest) {
        Kitchen currentKitchen = kitchenService.findOrNull(id);
        disassembler.<Kitchen, KitchenRequest>get(Kitchen.class).copyToEntity(kitchenRequest, currentKitchen);
        return assembler.<Kitchen, KitchenResponse>get(getParameters())
                .toModel(kitchenService.save(currentKitchen));
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Long id) {
        kitchenService.delete(id);
    }

    private AssemblerParameters<KitchenResponse> getParameters() {
        return new AssemblerParameters<>(KitchenResponse.class, this.getClass(), discover -> {
            discover.add(linkToKitchen(discover.getId()));
            discover.add(linkToKitchen("kitchens"));
        });
    }
}
