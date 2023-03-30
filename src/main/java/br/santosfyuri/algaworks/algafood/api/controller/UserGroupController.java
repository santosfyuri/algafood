package br.santosfyuri.algaworks.algafood.api.controller;

import br.santosfyuri.algaworks.algafood.api.assembler.AssemblerParameters;
import br.santosfyuri.algaworks.algafood.api.assembler.BasicAssembler;
import br.santosfyuri.algaworks.algafood.api.openapi.controller.UserGroupControllerOpenApi;
import br.santosfyuri.algaworks.algafood.api.representation.response.GroupResponse;
import br.santosfyuri.algaworks.algafood.domain.model.Group;
import br.santosfyuri.algaworks.algafood.domain.model.User;
import br.santosfyuri.algaworks.algafood.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static br.santosfyuri.algaworks.algafood.api.helpers.AlgaLinks.linkToGroup;

@RestController
@RequestMapping(value = "/users/{userId}/groups")
public class UserGroupController implements UserGroupControllerOpenApi {

    @Autowired
    private UserService userService;

    @Autowired
    private BasicAssembler assembler;

    @GetMapping
    public CollectionModel<GroupResponse> list(@PathVariable Long groupId) {
        User user = userService.findOrNull(groupId);

        return assembler.<Group, GroupResponse>get(getParameters())
                .toCollectionModel(user.getGroups());
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.disassociateGroup(userId, groupId);
    }

    @PutMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long userId, @PathVariable Long groupId) {
        userService.asassociateGroup(userId, groupId);
    }

    private AssemblerParameters<GroupResponse> getParameters() {
        return new AssemblerParameters<>(GroupResponse.class, this.getClass(), discover ->
                discover.add(linkToGroup(discover.getId())));
    }
}
