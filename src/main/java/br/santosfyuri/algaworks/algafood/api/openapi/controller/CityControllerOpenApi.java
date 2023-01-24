package br.santosfyuri.algaworks.algafood.api.openapi.controller;

import br.santosfyuri.algaworks.algafood.domain.model.City;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Cities")
public interface CityControllerOpenApi {

    @Operation(summary = "List all cities")
    List<City> list();

    @Operation(summary = "Search a city by ID", responses = {
            @ApiResponse(responseCode = "400", description = "City ID invalid"),
            @ApiResponse(responseCode = "404", description = "City not found")
    })
    City find(@Parameter(description = "City ID", example = "1", required = true) Long id);

    @Operation(summary = "Register a city", responses = {
            @ApiResponse(responseCode = "201", description = "Registered city")
    })
    City save(@RequestBody(description = "New city representation", required = true) City city);

    @Operation(summary = "Update a city by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Updated city"),
            @ApiResponse(responseCode = "404", description = "City not found")
    })
    City update(@Parameter(description = "City ID", example = "1", required = true) Long id,
                @RequestBody(description = "New city representation", required = true) City city);

    @Operation(summary = "Remove a city by ID", responses = {
            @ApiResponse(responseCode = "204", description = "Removed city"),
            @ApiResponse(responseCode = "404", description = "City not found")
    })
    void delete(@Parameter(description = "City ID", example = "1", required = true) Long id);
}
