package br.santosfyuri.algaworks.algafood.domain.repository;

import br.santosfyuri.algaworks.algafood.domain.model.Restaurant;

import java.math.BigDecimal;
import java.util.List;

public interface CustomizedRestaurantRepository {

    List<Restaurant> find(String name, BigDecimal startingFee, BigDecimal finalFee);

    List<Restaurant> findWithFreeShipping(String name);
}
