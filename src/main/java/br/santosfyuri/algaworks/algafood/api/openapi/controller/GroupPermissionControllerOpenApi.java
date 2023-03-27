package br.santosfyuri.algaworks.algafood.api.openapi.controller;

import br.santosfyuri.algaworks.algafood.api.representation.response.PermissionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Group permissions")
public interface GroupPermissionControllerOpenApi {

    @Operation(summary = "List all group permissions", responses = {
            @ApiResponse(responseCode = "400", description = "Invalid group ID", content = {
                    @Content(schema = @Schema(ref = "Problem"))}),
            @ApiResponse(responseCode = "404", description = "Group not found", content = {
                    @Content(schema = @Schema(ref = "Problem"))}),
    })
    List<PermissionResponse> list(@Parameter(description = "Group ID", example = "1", required = true) Long groupId);

    @Operation(summary = "Disassociation of the group with the permission", responses = {
            @ApiResponse(responseCode = "204", description = "Disassociation done successfully"),
            @ApiResponse(responseCode = "404", description = "Group or permission not found")
    })
    void disassociate(@Parameter(description = "Group ID", example = "1", required = true) Long groupId,
                      @Parameter(description = "Permission ID", example = "1", required = true) Long permissionId);

    @Operation(summary = "Association of the group with permission", responses = {
            @ApiResponse(responseCode = "204", description = "Association done successfully"),
            @ApiResponse(responseCode = "404", description = "Group or permission not found")
    })
    void associate(@Parameter(description = "Group ID", example = "1", required = true) Long groupId,
                   @Parameter(description = "Permission ID", example = "1", required = true) Long permissionId);
}
