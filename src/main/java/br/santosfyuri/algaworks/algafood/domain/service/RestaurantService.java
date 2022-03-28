package br.santosfyuri.algaworks.algafood.domain.service;

import br.santosfyuri.algaworks.algafood.domain.exception.EntityInUseException;
import br.santosfyuri.algaworks.algafood.domain.exception.EntityNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.model.Kitchen;
import br.santosfyuri.algaworks.algafood.domain.model.Restaurant;
import br.santosfyuri.algaworks.algafood.domain.repository.KitchenRepository;
import br.santosfyuri.algaworks.algafood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private KitchenRepository kitchenRepository;

    public Restaurant save(Restaurant restaurant) {
        Kitchen kitchen = kitchenRepository.findById(restaurant.getKitchen().getId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("Não existe cadastro de cozinha com código %d", restaurant.getKitchen().getId())));
        restaurant.setKitchen(kitchen);
        return restaurantRepository.save(restaurant);
    }

    public void delete(Long id) {
        try {
            restaurantRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(String.format("Não existe um cadastro de restaurante com código %d", id));
        } catch (DataIntegrityViolationException exception) {
            throw new EntityInUseException(String.format("Restaurante de código %d não pode ser removido, pois está em uso.", id));
        }
    }
}
