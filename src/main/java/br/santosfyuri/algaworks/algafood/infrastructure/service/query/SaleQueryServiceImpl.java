package br.santosfyuri.algaworks.algafood.infrastructure.service.query;

import br.santosfyuri.algaworks.algafood.domain.dto.DailySale;
import br.santosfyuri.algaworks.algafood.domain.enums.OrderStatus;
import br.santosfyuri.algaworks.algafood.domain.filter.DailySaleFilter;
import br.santosfyuri.algaworks.algafood.domain.service.SaleQueryService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
public class SaleQueryServiceImpl implements SaleQueryService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DailySale> consultDailySale(DailySaleFilter filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DailySale> query = builder.createQuery(DailySale.class);
        Root<Order> root = query.from(Order.class);

        Expression<Date> functionCreatedAt = builder.function(
                "date", Date.class, root.get("createdAt"));

        CompoundSelection<DailySale> selection = builder.construct(DailySale.class,
                functionCreatedAt,
                builder.count(root.get("id")),
                builder.sum(root.get("total")));

        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(filter.getRestaurantId())) {
            predicates.add(builder.equal(root.get("restaurant"), filter.getRestaurantId()));
        }
        if (Objects.nonNull(filter.getCreatedAtBegin())) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("createdAt"), filter.getCreatedAtBegin()));
        }
        if (Objects.nonNull(filter.getCreatedAtEnd())) {
            predicates.add(builder.equal(root.get("createdAt"), filter.getCreatedAtEnd()));
        }

        predicates.add(root.get("status").in(OrderStatus.CONFIRMADO, OrderStatus.ENTREGUE));

        query.select(selection);
        query.where(predicates.toArray(new Predicate[0]));
        query.groupBy(functionCreatedAt);

        return entityManager.createQuery(query).getResultList();
    }
}
