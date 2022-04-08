package br.santosfyuri.algaworks.algafood.api.controller;

import br.santosfyuri.algaworks.algafood.api.assembler.RestaurantInputDisassembler;
import br.santosfyuri.algaworks.algafood.api.assembler.RestaurantModelAssembler;
import br.santosfyuri.algaworks.algafood.api.model.RestaurantModel;
import br.santosfyuri.algaworks.algafood.api.model.input.KitchenIdInput;
import br.santosfyuri.algaworks.algafood.api.model.input.RestaurantInput;
import br.santosfyuri.algaworks.algafood.domain.exception.BusinessException;
import br.santosfyuri.algaworks.algafood.domain.exception.KitchenNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.exception.ValidationException;
import br.santosfyuri.algaworks.algafood.domain.model.Restaurant;
import br.santosfyuri.algaworks.algafood.domain.repository.RestaurantRepository;
import br.santosfyuri.algaworks.algafood.domain.service.RestaurantService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private SmartValidator smartValidator;

    @Autowired
    private RestaurantModelAssembler assembler;

    @Autowired
    private RestaurantInputDisassembler disassembler;

    @GetMapping
    public List<RestaurantModel> list() {
        return assembler.toCollectionModel(restaurantRepository.findAll());
    }

    @GetMapping(path = "{id}")
    public RestaurantModel find(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.findOrNull(id);

        return assembler.toModel(restaurant);
    }

    @PostMapping
    public RestaurantModel save(@RequestBody @Valid RestaurantInput input) {
        try {
            Restaurant restaurant = disassembler.toDomainObject(input);
            return assembler.toModel(restaurantService.save(restaurant));
        } catch (KitchenNotFoundException exception) {
            throw new BusinessException(exception.getMessage());
        }
    }

    @PutMapping(path = "{id}")
    public RestaurantModel update(@PathVariable Long id,
                                  @RequestBody @Valid RestaurantInput input) {
        try {
            Restaurant currentRestaurant = restaurantService.findOrNull(id);
            disassembler.copyToDomainObject(input, currentRestaurant);
            return assembler.toModel(restaurantService.save(currentRestaurant));
        } catch (KitchenNotFoundException exception) {
            throw new BusinessException(exception.getMessage());
        }
    }

    @Deprecated
    @PatchMapping(path = "{id}")
    public RestaurantModel atualizarParcial(@PathVariable Long id,
                                            @RequestBody Map<String, Object> fields, HttpServletRequest request) {
        Restaurant currentRestaurant = restaurantService.findOrNull(id);
        merge(fields, currentRestaurant, request);
        validate(currentRestaurant);

        RestaurantInput input = new RestaurantInput();
        input.setName(currentRestaurant.getName());
        input.setDeliveryFee(currentRestaurant.getDeliveryFee());

        KitchenIdInput kitchenIdInput = new KitchenIdInput();
        kitchenIdInput.setId(currentRestaurant.getKitchen().getId());

        input.setKitchen(kitchenIdInput);

        return update(id, input);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        restaurantService.delete(id);
    }

    private void merge(Map<String, Object> dataOrigin, Restaurant restaurantDestiny,
                       HttpServletRequest request) {
        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

            Restaurant restaurantOrigin = objectMapper.convertValue(dataOrigin, Restaurant.class);

            dataOrigin.forEach((propertyName, propertyValue) -> {
                Field field = ReflectionUtils.findField(Restaurant.class, propertyName);
                Objects.requireNonNull(field).setAccessible(true);

                Object novoValor = ReflectionUtils.getField(field, restaurantOrigin);

                ReflectionUtils.setField(field, restaurantDestiny, novoValor);
            });
        } catch (IllegalArgumentException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
        }
    }

    private void validate(Restaurant restaurant) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurant,
                restaurant.getClass().getSimpleName());
        smartValidator.validate(restaurant, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
    }
}
