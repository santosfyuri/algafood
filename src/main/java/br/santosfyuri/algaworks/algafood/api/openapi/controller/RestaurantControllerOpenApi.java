package br.santosfyuri.algaworks.algafood.api.openapi.controller;

import br.santosfyuri.algaworks.algafood.api.representation.request.RestaurantRequest;
import br.santosfyuri.algaworks.algafood.api.representation.response.RestaurantResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

public interface RestaurantControllerOpenApi {


    @Operation(summary = "List all restaurants", parameters = {
            @Parameter(name = "projection",
                    description = "Projection name",
                    example = "only name",
                    in = ParameterIn.QUERY
            )
    })
    List<RestaurantResponse> list();

    @Operation(summary = "Find restaurant by ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid restaurant ID", content = {
                    @Content(schema = @Schema(ref = "Problem"))}),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = {
                    @Content(schema = @Schema(ref = "Problem"))}),
    })
    RestaurantResponse find(@Parameter(description = "Restaurant ID", example = "1", required = true) Long id);

    @Operation(summary = "Register restaurant", responses = {
            @ApiResponse(responseCode = "201", description = "Restaurant registered"),
    })
    RestaurantResponse save(@RequestBody(description = "New restaurant representation", required = true) RestaurantRequest input);

    @Operation(summary = "Update restaurant by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Restaurant updated"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = {
                    @Content(schema = @Schema(ref = "Problem"))}),
    })
    RestaurantResponse update(@Parameter(description = "Restaurant ID", example = "1", required = true) Long id,
                              @RequestBody(description = "New restaurant representation", required = true) RestaurantRequest input);

    @Operation(summary = "Open restaurant by ID", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurant opened"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = {
                    @Content(schema = @Schema(ref = "Problem"))}),
    })
    void abrir(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restauranteId);

    @Operation(summary = "Close restaurant by ID", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurant closed"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = {
                    @Content(schema = @Schema(ref = "Problem"))}),
    })
    void fechar(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restauranteId);

    @Operation(summary = "Activate restaurant by ID", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurant activated"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = {
                    @Content(schema = @Schema(ref = "Problem"))}),
    })
    void activate(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restauranteId);


    @Operation(summary = "Disable restaurant by ID", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurant disabled"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = {
                    @Content(schema = @Schema(ref = "Problem"))}),
    })
    void inactivate(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restauranteId);

    @Operation(summary = "Activate multiple restaurants", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurant activated")
    })
    void activations(@RequestBody(description = "Restaurant IDs", required = true) List<Long> restauranteIds);

    @Operation(summary = "Disable multiple restaurants", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurant disabled")
    })
    void inactivations(@RequestBody(description = "Restaurant IDs", required = true) List<Long> restauranteIds);
}
