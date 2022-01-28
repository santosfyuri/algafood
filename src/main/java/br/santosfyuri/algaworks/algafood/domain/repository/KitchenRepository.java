package br.santosfyuri.algaworks.algafood.domain.repository;

import br.santosfyuri.algaworks.algafood.domain.model.Kitchen;

import java.util.List;

public interface KitchenRepository {

    List<Kitchen> list();

    Kitchen find(Long id);

    Kitchen save(Kitchen kitchen);

    void delete(Long id);
}
