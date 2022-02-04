package br.santosfyuri.algaworks.algafood.domain.repository;

import br.santosfyuri.algaworks.algafood.domain.model.City;

import java.util.List;

public interface CityRepository {

    List<City> list();

    City find(Long id);

    City save(City city);

    void delete(Long id);
}
