package br.santosfyuri.algaworks.algafood.api.openapi.controller;

import br.santosfyuri.algaworks.algafood.api.representation.request.UserRequest;
import br.santosfyuri.algaworks.algafood.api.representation.response.UserResponse;
import br.santosfyuri.algaworks.algafood.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

public interface UserControllerOpenApi {

    @Operation(summary = "List all users")
    List<User> list();

    @Operation(summary = "List a user by ID", responses = {
            @ApiResponse(responseCode = "400", description = "Invalid user ID", content = {
                    @Content(schema = @Schema(ref = "Problem"))}),
            @ApiResponse(responseCode = "404", description = "User not found", content = {
                    @Content(schema = @Schema(ref = "Problem"))}),
    })
    UserResponse find(@Parameter(description = "User ID", example = "1", required = true) Long id);

    @Operation(summary = "Register user", responses = {
            @ApiResponse(responseCode = "201", description = "User registered"),
    })
    UserResponse save(@RequestBody(description = "New user representation", required = true) UserRequest userRequest);

    @Operation(summary = "Update user by ID", responses = {
            @ApiResponse(responseCode = "200", description = "User updated"),
            @ApiResponse(responseCode = "404", description = "User not found", content = {
                    @Content(schema = @Schema(ref = "Problem"))}),
    })
    UserResponse update(@Parameter(description = "User ID", example = "1", required = true) Long id,
                        @RequestBody(description = "New user representation", required = true) UserRequest userRequest);

    @Operation(summary = "Remove a user by ID", responses = {
            @ApiResponse(responseCode = "204", description = "Removed user"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    void delete(@Parameter(description = "User ID", example = "1", required = true) Long id);
}
