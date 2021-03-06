package br.santosfyuri.algaworks.algafood.api.controller;

import br.santosfyuri.algaworks.algafood.domain.exception.BusinessException;
import br.santosfyuri.algaworks.algafood.domain.exception.EntityNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.exception.StateNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.model.City;
import br.santosfyuri.algaworks.algafood.domain.repository.CityRepository;
import br.santosfyuri.algaworks.algafood.domain.service.CityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityService cityService;

    @GetMapping
    public List<City> list() {
        return cityRepository.findAll();
    }

    @GetMapping(path = "{id}")
    public City find(@PathVariable Long id) {
        return cityService.findOrNull(id);
    }

    @PostMapping
    public City save(@RequestBody @Valid City city) {
        try {
            return cityService.save(city);
        } catch (StateNotFoundException exception) {
            throw new BusinessException(exception.getMessage(), exception);
        }
    }

    @PutMapping(path = "{id}")
    public City update(@PathVariable Long id,
                       @RequestBody @Valid City city) {
        try {
            City currentCity = cityService.findOrNull(id);
            BeanUtils.copyProperties(city, currentCity, "id");
            return cityService.save(currentCity);
        } catch (StateNotFoundException exception) {
            throw new BusinessException(exception.getMessage(), exception);
        }
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Long id) {
        cityService.delete(id);
    }
}
