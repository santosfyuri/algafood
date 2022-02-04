package br.santosfyuri.algaworks.algafood.api.controller;

import br.santosfyuri.algaworks.algafood.domain.exception.EntityInUseException;
import br.santosfyuri.algaworks.algafood.domain.exception.EntityNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.model.Kitchen;
import br.santosfyuri.algaworks.algafood.domain.model.Restaurant;
import br.santosfyuri.algaworks.algafood.domain.repository.RestaurantRepository;
import br.santosfyuri.algaworks.algafood.domain.service.RestaurantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> list() {
        return restaurantRepository.list();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Restaurant> find(@PathVariable Long id) {
        Restaurant restaurant = restaurantRepository.find(id);
        if (Objects.nonNull(restaurant)) {
            return ResponseEntity.ok(restaurant);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Restaurant restaurant) {
        try {
            restaurant = restaurantService.save(restaurant);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restaurant);
        } catch(EntityNotFoundException exception) {
            return ResponseEntity.badRequest()
                    .body(exception.getMessage());
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody Restaurant restaurant) {
        try {
            Restaurant currentRestaurant = restaurantRepository.find(id);
            if (Objects.nonNull(currentRestaurant)) {
                BeanUtils.copyProperties(restaurant, currentRestaurant, "id");
                currentRestaurant = restaurantService.save(currentRestaurant);
                return ResponseEntity.ok(currentRestaurant);
            }
            return ResponseEntity.notFound().build();
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.badRequest()
                    .body(exception.getMessage());
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Kitchen> delete(@PathVariable(value = "id") Long id) {
        try {
            restaurantService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.notFound().build();
        } catch (EntityInUseException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
