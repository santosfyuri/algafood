package br.santosfyuri.algaworks.algafood.api.openapi.controller;

import br.santosfyuri.algaworks.algafood.api.representation.response.GroupResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.hateoas.CollectionModel;

public interface UserGroupControllerOpenApi {

    @Operation(summary = "List all users groups by group ID")
    public CollectionModel<GroupResponse> list(@Parameter(description = "User ID", example = "1", required = true) Long groupId);


    @Operation(summary = "Disassociation of the user with group", responses = {
            @ApiResponse(responseCode = "204", description = "Association done successfully"),
            @ApiResponse(responseCode = "404", description = "Group or permission not found")
    })
    public void disassociate(@Parameter(description = "User ID", example = "1", required = true) Long userId,
                             @Parameter(description = "Group ID", example = "1", required = true) Long groupId);

    @Operation(summary = "Association of the user with group", responses = {
            @ApiResponse(responseCode = "204", description = "Removed user"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public void associate(@Parameter(description = "User ID", example = "1", required = true) Long userId,
                          @Parameter(description = "Group ID", example = "1", required = true) Long groupId);
}
