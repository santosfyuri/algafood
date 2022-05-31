package br.santosfyuri.algaworks.algafood.api.controller;

import br.santosfyuri.algaworks.algafood.api.assembler.BasicAssembler;
import br.santosfyuri.algaworks.algafood.api.representation.request.ProductPhotoRequest;
import br.santosfyuri.algaworks.algafood.api.representation.response.ProductPhotoResponse;
import br.santosfyuri.algaworks.algafood.domain.exception.EntityNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.model.Product;
import br.santosfyuri.algaworks.algafood.domain.model.ProductPhoto;
import br.santosfyuri.algaworks.algafood.domain.service.PhotoStorageService;
import br.santosfyuri.algaworks.algafood.domain.service.ProductPhotoCatalogService;
import br.santosfyuri.algaworks.algafood.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductPhotoCatalogService productPhotoCatalogService;

    @Autowired
    private PhotoStorageService photoStorageService;

    @Autowired
    private BasicAssembler assembler;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductPhotoResponse updatePhoto(@PathVariable Long restaurantId,
                                            @PathVariable Long productId,
                                            @RequestParam ProductPhotoRequest productPhotoRequest) throws IOException {
        Product product = productService.findOrNull(restaurantId, productId);

        MultipartFile file = productPhotoRequest.getArchive();

        ProductPhoto photo = ProductPhoto.create()
                .product(product)
                .description(productPhotoRequest.getDescription())
                .contentType(file.getContentType())
                .size(file.getSize())
                .fileName(file.getOriginalFilename())
                .build();

        return assembler.<ProductPhoto, ProductPhotoResponse>get(ProductPhotoResponse.class)
                .entityToRepresentation(productPhotoCatalogService.save(photo, file.getInputStream()));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductPhotoResponse find(@PathVariable Long restaurantId,
                                     @PathVariable Long productId) {
        ProductPhoto productPhoto = productPhotoCatalogService.findOrNull(restaurantId, productId);
        return assembler.<ProductPhoto, ProductPhotoResponse>get(ProductPhotoResponse.class)
                .entityToRepresentation(productPhoto);
    }

    @GetMapping
    public ResponseEntity<InputStreamResource> findPhoto(@PathVariable Long restaurantId,
                                                         @PathVariable Long productId,
                                                         @RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {
        try {
            ProductPhoto productPhoto = productPhotoCatalogService.findOrNull(restaurantId, productId);

            MediaType mediaType = MediaType.parseMediaType(productPhoto.getContentType());
            List<MediaType> acceptMediaTypes = MediaType.parseMediaTypes(acceptHeader);

            checkMediaTypeCompatibility(mediaType, acceptMediaTypes);

            PhotoStorageService.RetrievedPhoto retrievedPhoto = photoStorageService.toRetrieve(productPhoto.getFileName());

            if (retrievedPhoto.hasURL()) {
                return ResponseEntity.status(HttpStatus.FOUND)
                        .header(HttpHeaders.LOCATION, retrievedPhoto.getUrl())
                        .build();
            } else {
                return ResponseEntity.ok()
                        .contentType(mediaType)
                        .body(new InputStreamResource(retrievedPhoto.getInputStream()));
            }

        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long restaurantId,
                       @PathVariable Long productId) {
        productPhotoCatalogService.delete(restaurantId, productId);
    }

    private void checkMediaTypeCompatibility(MediaType mediaType,
                                              List<MediaType> acceptMediaTypes) throws HttpMediaTypeNotAcceptableException {
        boolean compatibility = acceptMediaTypes.stream().anyMatch(acceptMediaType -> acceptMediaType.isCompatibleWith(mediaType));
        if (!compatibility) {
            throw new HttpMediaTypeNotAcceptableException(acceptMediaTypes);
        }
    }
}
