package br.santosfyuri.algaworks.algafood.domain.service;

import br.santosfyuri.algaworks.algafood.domain.exception.ProductNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.model.ProductPhoto;
import br.santosfyuri.algaworks.algafood.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Optional;

@Service
public class ProductPhotoCatalogService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PhotoStorageService photoStorageService;

    public ProductPhoto findOrNull(Long restaurantId, Long productId) {
        return productRepository.findPhotoById(restaurantId, productId)
                .orElseThrow(() -> new ProductNotFoundException(restaurantId, productId));
    }


    @Transactional
    public ProductPhoto save(ProductPhoto productPhoto, InputStream inputStream) {
        String existingFileName = null;
        Optional<ProductPhoto> existingPhoto = productRepository.findPhotoById(productPhoto.getRestaurantId(),
                productPhoto.getProduct().getId());

        if (existingPhoto.isPresent()) {
            existingFileName = existingPhoto.get().getFileName();
            productRepository.delete(existingPhoto.get());
        }

        productPhoto = productRepository.save(productPhoto.toBuilder()
                .fileName(photoStorageService.generateFileName(productPhoto.getFileName()))
                .build());
        productRepository.flush();

        PhotoStorageService.NewPhoto newPhoto = PhotoStorageService.NewPhoto.create()
                .fileName(productPhoto.getFileName())
                .inputStream(inputStream)
                .contentType(productPhoto.getContentType())
                .build();

        photoStorageService.toReplace(existingFileName, newPhoto);

        return productPhoto;
    }

    @Transactional
    public void delete(Long restaurantId, Long productId) {
        ProductPhoto productPhoto = findOrNull(restaurantId, productId);

        productRepository.delete(productPhoto);
        productRepository.flush();

        photoStorageService.delete(productPhoto.getFileName());
    }
}
