package br.santosfyuri.algaworks.algafood.domain.persistence;

import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BasicRepository<T extends Identity<Long>> implements IRepository<T> {

    private static final String ENTITY_MUST_NOT_BE_NULL = "A(s) entidade(s) deve(m) ser informada(s).";

    private static final String ID_MUST_NOT_BE_NULL = "O identificador deve ser informado";

    @PersistenceContext
    private final EntityManager entityManager;

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public BasicRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.type = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), BasicRepository.class);
    }

    @Override
    public Optional<T> find(Serializable id) {
        Assert.notNull(type, "Type is required.");
        return Optional.ofNullable(entityManager.find(type, id));
    }

    @Override
    public List<T> find() {
        Assert.notNull(type, "Type is required.");
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);

        final Root<T> root = criteriaQuery.from(type);
        criteriaQuery.select(root);

        final TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Transactional
    @Override
    public <S extends T> S save(S entity) {
        Assert.notNull(entity, ENTITY_MUST_NOT_BE_NULL);
        if (entity.isNew()) {
            entityManager.persist(entity);
            return entity;
        } else {
            return entityManager.merge(entity);
        }
    }

    @Transactional
    @Override
    public <S extends T> List<S> save(Iterable<S> entites) {
        Assert.notNull(entites, ENTITY_MUST_NOT_BE_NULL);

        List<S> result = new ArrayList<>();
        entites.forEach(entity -> result.add(save(entity)));
        return result;
    }

    @Transactional
    @Override
    public void remove(T entity) {
        Assert.notNull(entity, ENTITY_MUST_NOT_BE_NULL);

        if (entity.isNew()) {
            return;
        }
        entityManager.remove(entity);
    }

    @Transactional
    @Override
    public void remove(Long id) {
        Assert.notNull(id, ID_MUST_NOT_BE_NULL);

        final Optional<T> managedEntity = find(id);
        managedEntity.ifPresent(this::remove);
    }

    @Transactional
    @Override
    public void remove(Iterable<? extends T> entities) {
        Assert.notNull(entities, ENTITY_MUST_NOT_BE_NULL);
        entities.forEach(this::remove);
    }
}
