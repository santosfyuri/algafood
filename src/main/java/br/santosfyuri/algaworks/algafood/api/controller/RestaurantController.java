package br.santosfyuri.algaworks.algafood.api.controller;

import br.santosfyuri.algaworks.algafood.api.assembler.BasicAssembler;
import br.santosfyuri.algaworks.algafood.api.assembler.BasicDisassembler;
import br.santosfyuri.algaworks.algafood.api.representation.request.KitchenIdRequest;
import br.santosfyuri.algaworks.algafood.api.representation.request.RestaurantRequest;
import br.santosfyuri.algaworks.algafood.api.representation.response.RestaurantResponse;
import br.santosfyuri.algaworks.algafood.domain.exception.BusinessException;
import br.santosfyuri.algaworks.algafood.domain.exception.KitchenNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.exception.RestaurantNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.exception.ValidationException;
import br.santosfyuri.algaworks.algafood.domain.model.Restaurant;
import br.santosfyuri.algaworks.algafood.domain.repository.RestaurantRepository;
import br.santosfyuri.algaworks.algafood.domain.service.RestaurantService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private BasicAssembler assembler;

    @Autowired
    private BasicDisassembler disassembler;

    @GetMapping
    public List<RestaurantResponse> list() {
        return assembler.<Restaurant, RestaurantResponse>get(RestaurantResponse.class)
                .entityToRepresentation(restaurantRepository.findAll());
    }

    @GetMapping(path = "{id}")
    public RestaurantResponse find(@PathVariable Long id) {
        return assembler.<Restaurant, RestaurantResponse>get(RestaurantResponse.class)
                .entityToRepresentation(restaurantService.findOrNull(id));
    }

    @PostMapping
    public RestaurantResponse save(@RequestBody @Valid RestaurantRequest input) {
        try {
            Restaurant restaurant = disassembler.<Restaurant, RestaurantRequest>get(Restaurant.class)
                    .representationToEntity(input);
            return assembler.<Restaurant, RestaurantResponse>get(RestaurantResponse.class)
                    .entityToRepresentation(restaurantService.save(restaurant));
        } catch (KitchenNotFoundException | RestaurantNotFoundException exception) {
            throw new BusinessException(exception.getMessage());
        }
    }

    @PutMapping(path = "{id}")
    public RestaurantResponse update(@PathVariable Long id,
                                     @RequestBody @Valid RestaurantRequest input) {
        try {
            Restaurant currentRestaurant = restaurantService.findOrNull(id);
            disassembler.<Restaurant, RestaurantRequest>get(Restaurant.class).copyToEntity(input, currentRestaurant);
            return assembler.<Restaurant, RestaurantResponse>get(RestaurantResponse.class)
                    .entityToRepresentation(restaurantService.save(currentRestaurant));
        } catch (KitchenNotFoundException | RestaurantNotFoundException exception) {
            throw new BusinessException(exception.getMessage());
        }
    }

    @Deprecated
    @PatchMapping(path = "{id}")
    public RestaurantResponse atualizarParcial(@PathVariable Long id,
                                               @RequestBody Map<String, Object> fields, HttpServletRequest request) {
        Restaurant currentRestaurant = restaurantService.findOrNull(id);
        merge(fields, currentRestaurant, request);
        validate(currentRestaurant);

        RestaurantRequest input = new RestaurantRequest();
        input.setName(currentRestaurant.getName());
        input.setDeliveryFee(currentRestaurant.getDeliveryFee());

        KitchenIdRequest kitchenIdInput = new KitchenIdRequest();
        kitchenIdInput.setId(currentRestaurant.getKitchen().getId());

        input.setKitchen(kitchenIdInput);

        return update(id, input);
    }

    @PutMapping("/{restaurantId}/opening")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abrir(@PathVariable Long restauranteId) {
        restaurantService.open(restauranteId);
    }

    @PutMapping("/{restauranteId}/closing")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fechar(@PathVariable Long restauranteId) {
        restaurantService.close(restauranteId);
    }

    @PutMapping("/{restaurantId}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable Long restauranteId) {
        restaurantService.active(restauranteId);
    }

    @DeleteMapping("/{restauranteId}/inactive")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactivate(@PathVariable Long restauranteId) {
        restaurantService.inactive(restauranteId);
    }

    @PutMapping("activations")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activations(@RequestBody List<Long> restauranteIds) {
        restaurantService.active(restauranteIds);
    }

    @DeleteMapping("/inactivations")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactivations(@RequestBody List<Long> restauranteIds) {
        try {
            restaurantService.inactive(restauranteIds);
        } catch (RestaurantNotFoundException exception) {
            throw new BusinessException(exception.getMessage(), exception);
        }
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
