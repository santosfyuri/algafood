package br.santosfyuri.algaworks.algafood.domain.repository;

import br.santosfyuri.algaworks.algafood.domain.model.ProductPhoto;

public interface ProductRepositoryQueries {

    ProductPhoto save(ProductPhoto productPhoto);

    void delete(ProductPhoto productPhoto);
}
