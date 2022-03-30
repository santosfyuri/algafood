package br.santosfyuri.algaworks.algafood.domain.repository;

import br.santosfyuri.algaworks.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository
        extends CustomJpaRepository<Restaurant, Long>, CustomizedRestaurantRepository,
        JpaSpecificationExecutor<Restaurant> {

    @Query("from Restaurant r left join fetch r.kitchen left join fetch r.paymentMethods")
    List<Restaurant> findAll();

    List<Restaurant> findByDeliveryFeeBetween(BigDecimal startingFee, BigDecimal finalFee);

    //    @Query("from Restaurant where name like %:nome% and kitchen.id = :kitchenId")
    List<Restaurant> findByNameAndKitchenId(String name, Long kitchenId);

//    List<Restaurant> findByNameContainingAndKitchenId(String name, Long kitchenId);

    Optional<Restaurant> findFirstByNameContaining(String name);
}
