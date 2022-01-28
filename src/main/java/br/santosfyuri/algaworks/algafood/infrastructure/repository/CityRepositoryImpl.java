package br.santosfyuri.algaworks.algafood.infrastructure.repository;

import br.santosfyuri.algaworks.algafood.domain.model.City;
import br.santosfyuri.algaworks.algafood.domain.repository.CityRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CityRepositoryImpl implements CityRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<City> list() {
        return manager.createQuery("from City", City.class).getResultList();
    }

    @Override
    public City find(Long id) {
        return manager.find(City.class, id);
    }

    @Transactional
    @Override
    public City save(City city) {
        return manager.merge(city);
    }

    @Transactional
    @Override
    public void delete(City city) {
        city = find(city.getId());
        manager.remove(city);
    }
}
