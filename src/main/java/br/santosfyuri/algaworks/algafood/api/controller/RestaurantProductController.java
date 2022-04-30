package br.santosfyuri.algaworks.algafood.api.controller;

import br.santosfyuri.algaworks.algafood.api.assembler.BasicAssembler;
import br.santosfyuri.algaworks.algafood.api.assembler.BasicDisassembler;
import br.santosfyuri.algaworks.algafood.api.representation.request.ProductRequest;
import br.santosfyuri.algaworks.algafood.api.representation.response.ProductResponse;
import br.santosfyuri.algaworks.algafood.domain.model.Product;
import br.santosfyuri.algaworks.algafood.domain.model.Restaurant;
import br.santosfyuri.algaworks.algafood.domain.repository.ProductRepository;
import br.santosfyuri.algaworks.algafood.domain.service.ProductService;
import br.santosfyuri.algaworks.algafood.domain.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products")
public class RestaurantProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private BasicAssembler assembler;

    @Autowired
    private BasicDisassembler disassembler;

    @GetMapping
    public List<ProductResponse> list(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findOrNull(restaurantId);
        List<Product> products = productRepository.findByRestaurant(restaurant);
        return assembler.<Product, ProductResponse>get(ProductResponse.class)
                .entityToRepresentation(products);
    }

    @GetMapping(path = "{id}")
    public ProductResponse find(@PathVariable Long restaurantId, @PathVariable Long productId) {
        return assembler.<Product, ProductResponse>get(ProductResponse.class)
                .entityToRepresentation(productService.findOrNull(restaurantId, productId));
    }

    @PostMapping
    public ProductResponse save(@PathVariable Long restaurantId,
                                @RequestBody @Valid ProductRequest productRequest) {
        Restaurant restaurant = restaurantService.findOrNull(restaurantId);
        Product product = disassembler.<Product, ProductRequest>get(Product.class)
                .representationToEntity(productRequest);
        product = product.toBuilder().restaurant(restaurant).build();
        return assembler.<Product, ProductResponse>get(ProductResponse.class)
                .entityToRepresentation(productService.save(product));
    }

    @PutMapping(path = "{id}")
    public ProductResponse update(@PathVariable Long restaurantId,
                                  @PathVariable Long productId,
                                  @RequestBody @Valid ProductRequest productRequest) {
        Product currentProduct = productService.findOrNull(restaurantId, productId);
        disassembler.<Product, ProductRequest>get(Product.class).copyToEntity(productRequest, currentProduct);
        return assembler.<Product, ProductResponse>get(ProductResponse.class)
                .entityToRepresentation(productService.save(currentProduct));
    }
}
