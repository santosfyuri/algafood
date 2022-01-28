package br.santosfyuri.algaworks.algafood.domain.repository;

import br.santosfyuri.algaworks.algafood.domain.model.State;

import java.util.List;

public interface StateRepository {

    List<State> list();

    State find(Long id);

    State save(State state);

    void delete(State state);
}
