package br.santosfyuri.algaworks.algafood.api.openapi.controller;

import br.santosfyuri.algaworks.algafood.api.representation.request.ProductPhotoRequest;
import br.santosfyuri.algaworks.algafood.api.representation.response.ProductPhotoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "Products")
public interface RestaurantProductPhotoControllerOpenApi {
    @Operation(description = "Update product photo", responses = {
            @ApiResponse(responseCode = "200", description = "Photo updated"),
            @ApiResponse(responseCode = "404", description = "Product photo not found", content = {
                    @Content(schema = @Schema(ref = "Problem"))})
    })
    ProductPhotoResponse updatePhoto(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId,
                                     @Parameter(description = "Product ID", example = "1", required = true) Long productId,
                                     ProductPhotoRequest productPhotoRequest,
                                     @Parameter(hidden = true) MultipartFile file) throws IOException;

    @Operation(description = "Exclui a foto do produto de um restaurante", responses = {
            @ApiResponse(responseCode = "204", description = "Product photo updated"),
            @ApiResponse(responseCode = "400", description = "Restaurant ID not found", content = {
                    @Content(schema = @Schema(ref = "Problem"))}),
            @ApiResponse(responseCode = "404", description = "Product photo not found", content = {
                    @Content(schema = @Schema(ref = "Problem"))})
    })
    void delete(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restauranteId,
                @Parameter(description = "Product ID", example = "1", required = true) Long produtoId);

    @Operation(description = "Find product photo", responses = {
            @ApiResponse(responseCode = "400", description = "ID do restaurante ou produto inválido", content = {
                    @Content(schema = @Schema(ref = "Problem"))}),
            @ApiResponse(responseCode = "404", description = "Foto de produto não encontrada", content = {
                    @Content(schema = @Schema(ref = "Problem"))})
    })
    ProductPhotoResponse find(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restauranteId,
                              @Parameter(description = "Product ID", example = "1", required = true) Long produtoId);

    @Operation(description = "Find product photo", hidden = true)
    ResponseEntity<?> findPhoto(Long restaurantId, Long productId, String acceptHeader)
            throws HttpMediaTypeNotAcceptableException;
}
