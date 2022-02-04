package br.santosfyuri.algaworks.algafood.domain.service;

import br.santosfyuri.algaworks.algafood.domain.exception.EntityInUseException;
import br.santosfyuri.algaworks.algafood.domain.exception.EntityNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.model.City;
import br.santosfyuri.algaworks.algafood.domain.model.State;
import br.santosfyuri.algaworks.algafood.domain.repository.CityRepository;
import br.santosfyuri.algaworks.algafood.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    public City save(City city) {
        State state = stateRepository.find(city.getState().getId());
        if (Objects.isNull(state)) {
            throw new EntityNotFoundException(
                    String.format("Não existe cadastro de estado com código %d", city.getState().getId()));
        }
        city.setState(state);
        return cityRepository.save(city);
    }

    public void delete(Long id) {
        try {
            cityRepository.delete(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntityNotFoundException(String.format("Não existe um cadastro de cidade com código %d", id));
        } catch (DataIntegrityViolationException exception) {
            throw new EntityInUseException(String.format("Cidade de código %d não pode ser removido, pois está em uso.", id));
        }
    }
}
