package br.santosfyuri.algaworks.algafood.infrastructure.repository;

import br.santosfyuri.algaworks.algafood.domain.model.Restaurant;
import br.santosfyuri.algaworks.algafood.domain.repository.CustomizedRestaurantRepository;
import br.santosfyuri.algaworks.algafood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.*;

import static br.santosfyuri.algaworks.algafood.infrastructure.repository.spec.RestaurantSpecs.withFreeShipping;
import static br.santosfyuri.algaworks.algafood.infrastructure.repository.spec.RestaurantSpecs.withSameName;

@Repository
public class RestaurantRepositoryImpl implements CustomizedRestaurantRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Lazy
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> find(String name, BigDecimal startingFee, BigDecimal finalFee) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("from Restaurant where 0 = 0");
        Map<String, Object> parameters = new HashMap<>();

        if (StringUtils.hasLength(name)) {
            jpql.append("where name like :name");
            parameters.put("name", "%" + name + "%");
        }
        if (Objects.nonNull(startingFee)) {
            jpql.append("and deliveryFee >= :startingFee");
            parameters.put("deliveryFee", startingFee);
        }
        if (Objects.nonNull(finalFee)) {
            jpql.append("and deliveryFee <= :finalFee");
            parameters.put("deliveryFee", finalFee);
        }

        TypedQuery<Restaurant> query = entityManager.createQuery(jpql.toString(), Restaurant.class);
        parameters.forEach(query::setParameter);
        return query.getResultList();
    }

    @Override
    public List<Restaurant> findWithFreeShipping(String name) {
        return restaurantRepository.findAll(withFreeShipping().and(withSameName(name)));
    }

    public List<Restaurant> findWithCriteria(String name, BigDecimal startingFee, BigDecimal finalFee) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
        Root<Restaurant> root = criteria.from(Restaurant.class);
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasLength(name)) {
            predicates.add(builder.like(root.get("name"), "%" + name + "%"));
        }
        if (Objects.nonNull(startingFee)) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("deliveryFee"), startingFee));
        }
        if (Objects.nonNull(finalFee)) {
            predicates.add(builder.lessThanOrEqualTo(root.get("deliveryFee"), finalFee));
        }

        criteria.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Restaurant> query = entityManager.createQuery(criteria);
        return query.getResultList();
    }


}
