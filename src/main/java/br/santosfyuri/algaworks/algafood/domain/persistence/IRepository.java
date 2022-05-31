package br.santosfyuri.algaworks.algafood.domain.persistence;

import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IRepository<T extends Identity<Long>> {

    Optional<T> find(Serializable id);

    List<T> find();

    @Transactional
    <S extends T> S save(S entity);

    <S extends T> List<S> save(Iterable<S> entites);

    void remove(T entity);

    void remove(Long id);

    void remove(Iterable<? extends T> entities);
}
