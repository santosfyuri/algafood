package br.santosfyuri.algaworks.algafood.domain.repository;

import br.santosfyuri.algaworks.algafood.domain.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodRepository {

    List<PaymentMethod> list();

    PaymentMethod find(Long id);

    PaymentMethod save(PaymentMethod paymentMethod);

    void delete(PaymentMethod paymentMethod);
}
