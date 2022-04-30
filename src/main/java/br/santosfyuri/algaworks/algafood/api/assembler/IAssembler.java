package br.santosfyuri.algaworks.algafood.api.assembler;

import java.util.Collection;
import java.util.List;

public interface IAssembler<E, R> {

    R entityToRepresentation(E entity);

    List<R> entityToRepresentation(Collection<E> entities);
}
