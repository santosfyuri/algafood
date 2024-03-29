package br.santosfyuri.algaworks.algafood.domain.repository;

import br.santosfyuri.algaworks.algafood.domain.model.Product;
import br.santosfyuri.algaworks.algafood.domain.model.ProductPhoto;
import br.santosfyuri.algaworks.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryQueries {

    @Query("from Product where restaurant.id = :restaurant and id = :product")
    Optional<Product> findById(@Param("restaurant") Long restaurantId,
                               @Param("product") Long productId);

    List<Product> findByRestaurant(Restaurant restaurant);

    @Query("select p from ProductPhoto p join p.product r where r.restaurant.id = :restaurantId and p.product.id = :productId")
    Optional<ProductPhoto> findPhotoById(Long restaurantId, Long productId);
}
