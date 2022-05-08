package br.santosfyuri.algaworks.algafood.infrastructure.repository.spec;

import br.santosfyuri.algaworks.algafood.domain.model.Order;
import br.santosfyuri.algaworks.algafood.domain.filter.OrderFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderSpecs {

    public static Specification<Order> usingFilter(OrderFilter orderFilter) {
        return (root, query, criteriaBuilder) -> {
            if (Order.class.equals(query.getResultType())) {
                root.fetch("restaurant").fetch("kitchen");
                root.fetch("client");
            }

            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(orderFilter.getClientId())) {
                predicates.add(criteriaBuilder.equal(root.get("client"), orderFilter.getClientId()));
            }
            if (Objects.nonNull(orderFilter.getRestaurantId())) {
                predicates.add(criteriaBuilder.equal(root.get("restaurant"), orderFilter.getRestaurantId()));
            }
            if (Objects.nonNull(orderFilter.getCreatedAtBegin())) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), orderFilter.getCreatedAtBegin()));
            }
            if (Objects.nonNull(orderFilter.getCreatedAtEnd())) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), orderFilter.getCreatedAtEnd()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
