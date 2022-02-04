package br.santosfyuri.algaworks.algafood.domain.repository;

import br.santosfyuri.algaworks.algafood.domain.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    List<Restaurant> list();

    Restaurant find(Long id);

    Restaurant save(Restaurant restaurant);

    void delete(Long id);
}
