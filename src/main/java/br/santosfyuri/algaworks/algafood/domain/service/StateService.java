package br.santosfyuri.algaworks.algafood.domain.service;

import br.santosfyuri.algaworks.algafood.domain.exception.EntityInUseException;
import br.santosfyuri.algaworks.algafood.domain.exception.StateNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.model.State;
import br.santosfyuri.algaworks.algafood.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StateService {

    private static final String MESSAGE_STATE_IN_USE = "Estado de código %d não pode ser removido, pois está em uso.";

    @Autowired
    private StateRepository stateRepository;

    @Transactional
    public State save(State state) {
        return stateRepository.save(state);
    }

    public State findOrNull(Long id) {
        return stateRepository.findById(id)
                .orElseThrow(() -> new StateNotFoundException(id));
    }

    @Transactional
    public void delete(Long id) {
        try {
            stateRepository.deleteById(id);
            stateRepository.flush();
        } catch (EmptyResultDataAccessException exception) {
            throw new StateNotFoundException(id);
        } catch (DataIntegrityViolationException exception) {
            throw new EntityInUseException(String.format(MESSAGE_STATE_IN_USE, id));
        }
    }
}
