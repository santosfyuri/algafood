package br.santosfyuri.algaworks.algafood.domain.service;

import br.santosfyuri.algaworks.algafood.domain.exception.EntityInUseException;
import br.santosfyuri.algaworks.algafood.domain.exception.StateNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.model.PaymentMethod;
import br.santosfyuri.algaworks.algafood.domain.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentMethodService {

    private static final String MESSAGE_PAYMENT_METHOD_IN_USE = "Forma de pagamento de código %d não pode ser removido, pois está em uso.";

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Transactional
    public PaymentMethod save(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    public PaymentMethod findOrNull(Long id) {
        return paymentMethodRepository.findById(id)
                .orElseThrow(() -> new StateNotFoundException(id));
    }

    @Transactional
    public void delete(Long id) {
        try {
            paymentMethodRepository.deleteById(id);
            paymentMethodRepository.flush();
        } catch (EmptyResultDataAccessException exception) {
            throw new StateNotFoundException(id);
        } catch (DataIntegrityViolationException exception) {
            throw new EntityInUseException(String.format(MESSAGE_PAYMENT_METHOD_IN_USE, id));
        }
    }
}
