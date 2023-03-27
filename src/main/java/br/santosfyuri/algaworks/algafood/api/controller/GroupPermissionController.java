package br.santosfyuri.algaworks.algafood.api.controller;

import br.santosfyuri.algaworks.algafood.api.assembler.BasicAssembler;
import br.santosfyuri.algaworks.algafood.api.openapi.controller.GroupPermissionControllerOpenApi;
import br.santosfyuri.algaworks.algafood.api.representation.response.PermissionResponse;
import br.santosfyuri.algaworks.algafood.domain.model.Group;
import br.santosfyuri.algaworks.algafood.domain.model.Permission;
import br.santosfyuri.algaworks.algafood.domain.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/groups/{groupId}/permissions")
public class GroupPermissionController implements GroupPermissionControllerOpenApi {

    @Autowired
    private GroupService groupService;

    @Autowired
    private BasicAssembler assembler;

    @GetMapping
    public List<PermissionResponse> list(@PathVariable Long groupId) {
        Group group = groupService.findOrNull(groupId);

        return assembler.<Permission, PermissionResponse>get(PermissionResponse.class)
                .entityToRepresentation(group.getPermissions());
    }

    @DeleteMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupService.disassociatePermission(groupId, permissionId);
    }

    @PutMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long groupId, @PathVariable Long permissionId) {
        groupService.asassociatePermission(groupId, permissionId);
    }
}
