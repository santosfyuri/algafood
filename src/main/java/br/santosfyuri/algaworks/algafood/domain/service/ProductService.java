package br.santosfyuri.algaworks.algafood.domain.service;

import br.santosfyuri.algaworks.algafood.domain.exception.ProductNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.model.Product;
import br.santosfyuri.algaworks.algafood.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product findOrNull(Long restaurantId, Long productId) {
        return productRepository.findById(restaurantId, productId)
                .orElseThrow(() -> new ProductNotFoundException(restaurantId, productId));
    }
}