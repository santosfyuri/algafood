package br.santosfyuri.algaworks.algafood.api.openapi.controller;

import br.santosfyuri.algaworks.algafood.api.representation.request.ProductRequest;
import br.santosfyuri.algaworks.algafood.api.representation.response.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Products")
public interface RestaurantProductControllerOpenApi {

    @Operation(summary = "List all restaurant products", responses = {
            @ApiResponse(responseCode = "400", description = "Invalid restaurant ID", content = {
                    @Content(schema = @Schema(ref = "Problem"))}),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = {
                    @Content(schema = @Schema(ref = "Problem"))}),
    })
    List<ProductResponse> list(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId);

    @Operation(summary = "Find restaurant by ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid restaurant ID or product", content = {
                    @Content(schema = @Schema(ref = "Problem"))}),
            @ApiResponse(responseCode = "404", description = "Restaurant product not found", content = {
                    @Content(schema = @Schema(ref = "Problem"))}),
    })
    ProductResponse find(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId,
                         @Parameter(description = "Product ID", example = "1", required = true) Long productId);

    @Operation(summary = "Register restaurant product", responses = {
            @ApiResponse(responseCode = "201", description = "Restaurant product registered"),
            @ApiResponse(responseCode = "404", description = "Restaurant product not found", content = {
                    @Content(schema = @Schema(ref = "Problem"))})
    })
    ProductResponse save(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId,
                         @RequestBody(description = "New restaurant product representation", required = true) ProductRequest productRequest);

    @Operation(summary = "Update restaurant product by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Restaurant product updated"),
            @ApiResponse(responseCode = "404", description = "Restaurant product not found", content = {
                    @Content(schema = @Schema(ref = "Problem"))}),
    })
    ProductResponse update(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId,
                           @Parameter(description = "Product ID", example = "1", required = true) Long productId,
                           @RequestBody(description = "New restaurant representation", required = true) ProductRequest productRequest);

}
