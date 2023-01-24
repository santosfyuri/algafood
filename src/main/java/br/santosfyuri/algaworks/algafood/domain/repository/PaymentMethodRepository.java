package br.santosfyuri.algaworks.algafood.domain.repository;

import br.santosfyuri.algaworks.algafood.domain.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    @Query("select max(updatedAt) from PaymentMethod")
    OffsetDateTime getLastUpdatedAt();

    @Query("select updatedAt from PaymentMethod where id = :paymentMethodId")
    OffsetDateTime getUpdatedAtById(Long paymentMethodId);
}
