package br.santosfyuri.algaworks.algafood.api.assembler;

import java.util.Collection;
import java.util.List;

public interface IDisassembler<E, R> {

    E representationToEntity(R representation);

    List<E> representationToEntity(Collection<R> representations);

    void copyToEntity(R representation, E entity);
}
