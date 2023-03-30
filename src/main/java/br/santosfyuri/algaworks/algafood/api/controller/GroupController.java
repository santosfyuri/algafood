package br.santosfyuri.algaworks.algafood.api.controller;

import br.santosfyuri.algaworks.algafood.api.assembler.AssemblerParameters;
import br.santosfyuri.algaworks.algafood.api.assembler.BasicAssembler;
import br.santosfyuri.algaworks.algafood.api.assembler.BasicDisassembler;
import br.santosfyuri.algaworks.algafood.api.openapi.controller.GroupControllerOpenApi;
import br.santosfyuri.algaworks.algafood.api.representation.request.GroupRequest;
import br.santosfyuri.algaworks.algafood.api.representation.response.GroupResponse;
import br.santosfyuri.algaworks.algafood.domain.model.Group;
import br.santosfyuri.algaworks.algafood.domain.repository.GroupRepository;
import br.santosfyuri.algaworks.algafood.domain.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static br.santosfyuri.algaworks.algafood.api.helpers.AlgaLinks.linkToGroup;

@RestController
@RequestMapping("/groups")
public class GroupController implements GroupControllerOpenApi {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private BasicAssembler assembler;

    @Autowired
    private BasicDisassembler disassembler;

    @GetMapping
    public CollectionModel<GroupResponse> list() {
        return assembler.<Group, GroupResponse>get(getParameters())
                .toCollectionModel(groupRepository.findAll());
    }

    @GetMapping(path = "{id}")
    public GroupResponse find(@PathVariable Long id) {
        return assembler.<Group, GroupResponse>get(getParameters())
                .toModel(groupService.findOrNull(id));
    }

    @PostMapping
    public GroupResponse save(@RequestBody @Valid GroupRequest input) {
        Group group = disassembler.<Group, GroupRequest>get(Group.class)
                .representationToEntity(input);
        return assembler.<Group, GroupResponse>get(getParameters())
                .toModel(groupService.save(group));
    }

    @PutMapping(path = "{id}")
    public GroupResponse update(@PathVariable Long id,
                                @RequestBody @Valid GroupRequest input) {
        Group currentGroup = groupService.findOrNull(id);
        disassembler.<Group, GroupRequest>get(Group.class).copyToEntity(input, currentGroup);
        return assembler.<Group, GroupResponse>get(getParameters())
                .toModel(groupService.save(currentGroup));
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        groupService.delete(id);
    }

    private AssemblerParameters<GroupResponse> getParameters() {
        return new AssemblerParameters<>(GroupResponse.class, this.getClass(),
                discover -> discover.add(linkToGroup(discover.getId())));
    }
}
