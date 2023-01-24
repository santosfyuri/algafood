package br.santosfyuri.algaworks.algafood.api.openapi.controller;

import br.santosfyuri.algaworks.algafood.api.representation.request.StateRequest;
import br.santosfyuri.algaworks.algafood.api.representation.response.StateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "States")
public interface StateControllerOpenApi {

    @Operation(summary = "List all states")
    List<StateResponse> list();

    @Operation(summary = "Search a state by ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "State ID invalid"),
            @ApiResponse(responseCode = "404", description = "State not found")
    })
    StateResponse find(@Parameter(description = "State ID", example = "1", required = true) Long id);

    @Operation(summary = "Register state by ID", responses = {
            @ApiResponse(responseCode = "201", description = "State registered")
    })
    StateResponse save(@RequestBody(description = "New state representation", required = true) StateRequest state);

    @Operation(summary = "Update state by ID", responses = {
            @ApiResponse(responseCode = "200", description = "State updated"),
            @ApiResponse(responseCode = "404", description = "State not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) })
    })
    StateResponse update(@Parameter(description = "State ID", example = "1", required = true) Long id,
                         @RequestBody(description = "New state representation", required = true) StateRequest state);

    @Operation(summary = "Delete state by ID", responses = {
            @ApiResponse(responseCode = "204", description = "State deleted"),
            @ApiResponse(responseCode = "404", description = "State not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) })
    })
    ResponseEntity<Void> delete(@Parameter(description = "State ID", example = "1", required = true) Long id);
}
