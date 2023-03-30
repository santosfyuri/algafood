package br.santosfyuri.algaworks.algafood.api.openapi.controller;

import br.santosfyuri.algaworks.algafood.api.representation.request.KitchenRequest;
import br.santosfyuri.algaworks.algafood.api.representation.response.KitchenResponse;
import br.santosfyuri.algaworks.algafood.core.openapi.PageableParameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@Tag(name = "Kitchens")
public interface KitchenControllerOpenApi {


    @Operation(summary = "List all kitchens")
    @PageableParameter
    PagedModel<KitchenResponse> list(@Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Search a kitchen by ID", responses = {
            @ApiResponse(responseCode = "400", description = "Kitchen ID invalid"),
            @ApiResponse(responseCode = "404", description = "Kitchen not found")
    })
    KitchenResponse find(@Parameter(description = "Kitchen ID", example = "1", required = true) Long id);

    @Operation(summary = "Register a kitchen", responses = {
            @ApiResponse(responseCode = "400", description = "Kitchen ID invalid"),
            @ApiResponse(responseCode = "404", description = "Kitchen not found")
    })
    KitchenResponse save(@RequestBody(description = "New kitchen representation", required = true) KitchenRequest kitchenRequest);

    @Operation(summary = "Update a kitchen by ID", responses = {
            @ApiResponse(responseCode = "400", description = "Kitchen ID invalid"),
            @ApiResponse(responseCode = "404", description = "Kitchen not found")
    })
    KitchenResponse update(@Parameter(description = "Kitchen ID", example = "1", required = true) Long id,
                           @RequestBody(description = "New kitchen representation", required = true) KitchenRequest kitchenRequest);

    @Operation(summary = "Remove a kitchen by ID", responses = {
            @ApiResponse(responseCode = "204", description = "Removed kitchen"),
            @ApiResponse(responseCode = "404", description = "Kitchen not found")
    })
    void delete(@Parameter(description = "Kitchen ID", example = "1", required = true) Long id);
}
