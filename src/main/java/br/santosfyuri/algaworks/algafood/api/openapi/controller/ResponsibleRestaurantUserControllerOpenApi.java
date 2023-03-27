package br.santosfyuri.algaworks.algafood.api.openapi.controller;

import br.santosfyuri.algaworks.algafood.api.representation.response.PaymentMethodResponse;
import br.santosfyuri.algaworks.algafood.api.representation.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Responsible restaurant users")
public interface ResponsibleRestaurantUserControllerOpenApi {
    @Operation(summary = "List all responsible users associated with restaurant", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurant not found")
    })
    List<UserResponse> list(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId);

    @Operation(summary = "Disassociation of the restaurant with responsible user", responses = {
            @ApiResponse(responseCode = "204", description = "Disassociation done successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant or user not found")
    })
    void disassociate(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId,
                      @Parameter(description = "User ID", example = "1", required = true) Long userId);


    @Operation(summary = "Association  of the restaurant with responsible user", responses = {
            @ApiResponse(responseCode = "204", description = "Association done successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant or user not found")
    })
    void associate(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId,
                     @Parameter(description = "User ID", example = "1", required = true) Long userId);
}
