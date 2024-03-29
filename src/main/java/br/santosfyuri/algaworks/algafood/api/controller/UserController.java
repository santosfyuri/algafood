package br.santosfyuri.algaworks.algafood.api.controller;

import br.santosfyuri.algaworks.algafood.api.assembler.AssemblerParameters;
import br.santosfyuri.algaworks.algafood.api.assembler.BasicAssembler;
import br.santosfyuri.algaworks.algafood.api.assembler.BasicDisassembler;
import br.santosfyuri.algaworks.algafood.api.openapi.controller.UserControllerOpenApi;
import br.santosfyuri.algaworks.algafood.api.representation.request.UserRequest;
import br.santosfyuri.algaworks.algafood.api.representation.response.UserResponse;
import br.santosfyuri.algaworks.algafood.domain.model.User;
import br.santosfyuri.algaworks.algafood.domain.repository.UserRepository;
import br.santosfyuri.algaworks.algafood.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static br.santosfyuri.algaworks.algafood.api.helpers.AlgaLinks.linkToUser;
import static br.santosfyuri.algaworks.algafood.api.helpers.AlgaLinks.linkToUserGroups;

@RestController
@RequestMapping("/users")
public class UserController implements UserControllerOpenApi {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BasicAssembler assembler;

    @Autowired
    private BasicDisassembler disassembler;

    @GetMapping
    public CollectionModel<UserResponse> list() {
        return assembler.<User, UserResponse>get(getParameters()).toCollectionModel(userRepository.findAll());
    }

    @GetMapping(path = "{id}")
    public UserResponse find(@PathVariable Long id) {
        return assembler.<User, UserResponse>get(getParameters())
                .toModel(userService.findOrNull(id));
    }

    @PostMapping
    public UserResponse save(@RequestBody @Valid UserRequest userRequest) {
        User user = disassembler.<User, UserRequest>get(User.class).representationToEntity(userRequest);
        return assembler.<User, UserResponse>get(getParameters())
                .toModel(userService.save(user));
    }

    @PutMapping(path = "{id}")
    public UserResponse update(@PathVariable Long id,
                               @RequestBody @Valid UserRequest userRequest) {
        User currentUser = userService.findOrNull(id);
        disassembler.<User, UserRequest>get(User.class).copyToEntity(userRequest, currentUser);
        return assembler.<User, UserResponse>get(getParameters())
                .toModel(userService.save(currentUser));
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Long id) {
        userService.delete(id);
    }

    private AssemblerParameters<UserResponse> getParameters() {
        return new AssemblerParameters<>(UserResponse.class, this.getClass(), discover -> {
            discover.add(linkToUser(discover.getId()));
            discover.add(linkToUser());
            discover.add(linkToUserGroups());
        });
    }
}
