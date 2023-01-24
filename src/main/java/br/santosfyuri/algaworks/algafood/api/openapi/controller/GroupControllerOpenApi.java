package br.santosfyuri.algaworks.algafood.api.openapi.controller;

import br.santosfyuri.algaworks.algafood.api.representation.request.GroupRequest;
import br.santosfyuri.algaworks.algafood.api.representation.response.GroupResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Groups")
public interface GroupControllerOpenApi {

    @Operation(summary = "List all groups")
    List<GroupResponse> list();

    @Operation(summary = "Search a group by ID", responses = {
            @ApiResponse(responseCode = "400", description = "Group ID invalid"),
            @ApiResponse(responseCode = "404", description = "Group not found")
    })
    GroupResponse find(@Parameter(description = "Group ID", example = "1", required = true) Long id);


    @Operation(summary = "Register a group", responses = {
            @ApiResponse(responseCode = "201", description = "Registered group")
    })
    GroupResponse save(@RequestBody(description = "New group representation", required = true) GroupRequest input);


    @Operation(summary = "Update a group by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Updated group"),
            @ApiResponse(responseCode = "404", description = "Group not found")
    })
    GroupResponse update(@Parameter(description = "Group ID", example = "1", required = true) @PathVariable Long id,
                         @RequestBody(description = "New group representation", required = true) GroupRequest input);

    @Operation(summary = "Remove a group by ID", responses = {
            @ApiResponse(responseCode = "204", description = "Removed group"),
            @ApiResponse(responseCode = "404", description = "Group not found")
    })
    void delete(@PathVariable(value = "id") Long id);
}