package br.santosfyuri.algaworks.algafood.infrastructure.repository;

import br.santosfyuri.algaworks.algafood.domain.model.ProductPhoto;
import br.santosfyuri.algaworks.algafood.domain.repository.ProductRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public ProductPhoto save(ProductPhoto productPhoto) {
        return entityManager.merge(productPhoto);
    }

    @Transactional
    @Override
    public void delete(ProductPhoto productPhoto) {
        entityManager.remove(productPhoto);
    }
}
