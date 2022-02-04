package br.santosfyuri.algaworks.algafood.api.controller;

import br.santosfyuri.algaworks.algafood.domain.exception.EntityInUseException;
import br.santosfyuri.algaworks.algafood.domain.exception.EntityNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.model.City;
import br.santosfyuri.algaworks.algafood.domain.model.State;
import br.santosfyuri.algaworks.algafood.domain.repository.CityRepository;
import br.santosfyuri.algaworks.algafood.domain.service.CityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/states")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityService cityService;

    @GetMapping
    public List<City> list() {
        return cityRepository.list();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<City> find(@PathVariable Long id) {
        City city = cityRepository.find(id);
        if (Objects.nonNull(city)) {
            return ResponseEntity.ok(city);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody City city) {
        try {
            city = cityService.save(city);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(city);
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.badRequest()
                    .body(exception.getMessage());
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody City city) {
        try {
            City currentCity = cityRepository.find(id);
            if (Objects.nonNull(currentCity)) {
                BeanUtils.copyProperties(city, currentCity, "id");
                currentCity = cityService.save(currentCity);
                return ResponseEntity.ok(currentCity);
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
            cityService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException exception) {
            return ResponseEntity.notFound().build();
        } catch (EntityInUseException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
