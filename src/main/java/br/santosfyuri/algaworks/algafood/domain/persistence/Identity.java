package br.santosfyuri.algaworks.algafood.domain.persistence;

import java.io.Serializable;
import java.util.Objects;

public interface Identity<T> extends Serializable {

    T getId();

    default boolean isNew() {
        return Objects.nonNull(getId());
    }
}
