package br.santosfyuri.algaworks.algafood.api.controller;

import br.santosfyuri.algaworks.algafood.domain.model.Kitchen;
import br.santosfyuri.algaworks.algafood.domain.repository.KitchenRepository;
import br.santosfyuri.algaworks.algafood.domain.service.KitchenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private KitchenService kitchenService;

    @GetMapping
    public List<Kitchen> list() {
        return kitchenRepository.findAll();
    }

    @GetMapping(path = "{id}")
    public Kitchen find(@PathVariable Long id) {
        return kitchenService.findOrNull(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kitchen save(@RequestBody @Valid Kitchen kitchen) {
        return kitchenService.save(kitchen);
    }

    @PutMapping(path = "{id}")
    public Kitchen update(@PathVariable Long id,
                          @RequestBody @Valid Kitchen kitchen) {
        Kitchen currentKitchen = kitchenService.findOrNull(id);
        BeanUtils.copyProperties(kitchen, currentKitchen, "id");
        return kitchenService.save(currentKitchen);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Long id) {
        kitchenService.delete(id);
    }
}
