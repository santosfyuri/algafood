package br.santosfyuri.algaworks.algafood.api.assembler;

import org.springframework.hateoas.CollectionModel;

public interface IAssembler<E, R> {

    R toModel(E entity);

    CollectionModel<R> toCollectionModel(Iterable<? extends E> entities);
}
