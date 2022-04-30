package br.santosfyuri.algaworks.algafood.domain.service;

import br.santosfyuri.algaworks.algafood.domain.exception.EntityInUseException;
import br.santosfyuri.algaworks.algafood.domain.exception.RestaurantNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.model.*;
import br.santosfyuri.algaworks.algafood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService {

    private static final String MESSAGE_RESTAURANT_IN_USE = "Restaurante de código %d não pode ser removido, pois está em uso.";

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private KitchenService kitchenService;

    @Autowired
    private CityService cityService;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private UserService userService;

    public Restaurant findOrNull(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        Kitchen kitchen = kitchenService.findOrNull(restaurant.getKitchen().getId());
        City city = cityService.findOrNull(restaurant.getAddress().getCity().getId());
        restaurant = restaurant.toBuilder()
                .kitchen(kitchen)
                .address(restaurant.getAddress().toBuilder()
                        .city(city)
                        .build())
                .build();
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public void active(Long id) {
        Restaurant currentRestaurant = findOrNull(id);
        currentRestaurant.active();
    }

    @Transactional
    public void inactive(Long id) {
        Restaurant currentRestaurant = findOrNull(id);
        currentRestaurant.inactive();
    }

    @Transactional
    public void active(List<Long> restaurantdIds) {
        restaurantdIds.forEach(this::active);
    }

    @Transactional
    public void inactive(List<Long> restaurantdIds) {
        restaurantdIds.forEach(this::inactive);
    }

    @Transactional
    public void delete(Long id) {
        try {
            restaurantRepository.deleteById(id);
            restaurantRepository.flush();
        } catch (EmptyResultDataAccessException exception) {
            throw new RestaurantNotFoundException(id);
        } catch (DataIntegrityViolationException exception) {
            throw new EntityInUseException(String.format(MESSAGE_RESTAURANT_IN_USE, id));
        }
    }

    @Transactional
    public void disassociatePaymentMethod(Long restaurantId, Long paymentMethodId) {
        Restaurant restaurant = findOrNull(restaurantId);
        PaymentMethod paymentMethod = paymentMethodService.findOrNull(paymentMethodId);
        restaurant.removePaymentMethod(paymentMethod);
    }

    @Transactional
    public void asassociatePaymentMethod(Long restaurantId, Long paymentMethodId) {
        Restaurant restaurant = findOrNull(restaurantId);
        PaymentMethod paymentMethod = paymentMethodService.findOrNull(paymentMethodId);
        restaurant.addPaymentMethod(paymentMethod);
    }

    @Transactional
    public void disassociateResponsibleUser(Long restaurantId, Long userId) {
        Restaurant restaurant = findOrNull(restaurantId);
        User user = userService.findOrNull(userId);
        restaurant.removeResponsibleUser(user);
    }

    @Transactional
    public void asassociateResponsibleUser(Long restaurantId, Long userId) {
        Restaurant restaurant = findOrNull(restaurantId);
        User user = userService.findOrNull(userId);
        restaurant.addResponsibleUser(user);
    }

    @Transactional
    public void open(Long restaurantId) {
        Restaurant currentRestaurant = findOrNull(restaurantId);
        currentRestaurant.open();
    }

    @Transactional
    public void close(Long restaurantId) {
        Restaurant currentRestaurant = findOrNull(restaurantId);
        currentRestaurant.close();
    }
}
