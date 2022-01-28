package br.santosfyuri.algaworks.algafood.infrastructure.repository;

import br.santosfyuri.algaworks.algafood.domain.model.Restaurant;
import br.santosfyuri.algaworks.algafood.domain.repository.RestaurantRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurant> list() {
        return manager.createQuery("from Restaurant", Restaurant.class).getResultList();
    }

    @Override
    public Restaurant find(Long id) {
        return manager.find(Restaurant.class, id);
    }

    @Transactional
    @Override
    public Restaurant save(Restaurant restaurant) {
        return manager.merge(restaurant);
    }

    @Transactional
    @Override
    public void delete(Restaurant restaurant) {
        restaurant = find(restaurant.getId());
        manager.remove(restaurant);
    }
}
