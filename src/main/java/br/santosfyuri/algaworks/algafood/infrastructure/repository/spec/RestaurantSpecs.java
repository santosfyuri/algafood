package br.santosfyuri.algaworks.algafood.infrastructure.repository.spec;

import br.santosfyuri.algaworks.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestaurantSpecs {

    public static Specification<Restaurant> withFreeShipping() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("deliveryFee"), BigDecimal.ZERO);
    }

    public static Specification<Restaurant> withSameName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }
}
