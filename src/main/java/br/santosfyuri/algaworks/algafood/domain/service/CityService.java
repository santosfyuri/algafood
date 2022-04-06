package br.santosfyuri.algaworks.algafood.domain.service;

import br.santosfyuri.algaworks.algafood.domain.exception.CityNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.exception.EntityInUseException;
import br.santosfyuri.algaworks.algafood.domain.model.City;
import br.santosfyuri.algaworks.algafood.domain.model.State;
import br.santosfyuri.algaworks.algafood.domain.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityService {

    private static final String MESSAGE_CITY_IN_USE = "Cidade de código %d não pode ser removido, pois está em uso.";

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateService stateService;

    public City findOrNull(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
    }

    @Transactional
    public City save(City city) {
        State state = stateService.findOrNull(city.getState().getId());
        city.setState(state);
        return cityRepository.save(city);
    }

    public void delete(Long id) {
        try {
            cityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new CityNotFoundException(id);
        } catch (DataIntegrityViolationException exception) {
            throw new EntityInUseException(String.format(MESSAGE_CITY_IN_USE, id));
        }
    }
}
