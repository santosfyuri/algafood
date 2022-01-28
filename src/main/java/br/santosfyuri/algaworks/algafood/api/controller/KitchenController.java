package br.santosfyuri.algaworks.algafood.api.controller;

import br.santosfyuri.algaworks.algafood.domain.exception.EntityInUseException;
import br.santosfyuri.algaworks.algafood.domain.exception.EntityNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.model.Kitchen;
import br.santosfyuri.algaworks.algafood.domain.repository.KitchenRepository;
import br.santosfyuri.algaworks.algafood.domain.service.KitchenRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private KitchenRegistrationService kitchenRegistrationService;

    @GetMapping
    public List<Kitchen> list() {
        return kitchenRepository.list();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kitchen save(@RequestBody Kitchen kitchen) {
        return kitchenRegistrationService.save(kitchen);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Kitchen> delete(@PathVariable(value = "id") Long id) {
        try {
            kitchenRegistrationService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.notFound().build();
        } catch (EntityInUseException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
