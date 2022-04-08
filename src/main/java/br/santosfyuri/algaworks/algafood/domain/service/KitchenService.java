package br.santosfyuri.algaworks.algafood.domain.service;

import br.santosfyuri.algaworks.algafood.domain.exception.EntityInUseException;
import br.santosfyuri.algaworks.algafood.domain.exception.KitchenNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.model.Kitchen;
import br.santosfyuri.algaworks.algafood.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KitchenService {

    private static final String MESSAGE_KITCHEN_IN_USE = "Cozinha de código %d não pode ser removida, pois está em uso.";

    @Autowired
    private KitchenRepository kitchenRepository;

    @Transactional
    public Kitchen save(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    public Kitchen findOrNull(Long id) {
        return kitchenRepository.findById(id)
                .orElseThrow(() -> new KitchenNotFoundException(id));
    }

    @Transactional
    public void delete(Long id) {
        try {
            kitchenRepository.deleteById(id);
            kitchenRepository.flush();
        } catch (EmptyResultDataAccessException exception) {
            throw new KitchenNotFoundException(id);
        } catch (DataIntegrityViolationException exception) {
            throw new EntityInUseException(String.format(MESSAGE_KITCHEN_IN_USE, id));
        }
    }
}
