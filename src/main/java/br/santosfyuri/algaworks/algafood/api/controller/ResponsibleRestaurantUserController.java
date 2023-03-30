package br.santosfyuri.algaworks.algafood.api.controller;

import br.santosfyuri.algaworks.algafood.api.assembler.AssemblerParameters;
import br.santosfyuri.algaworks.algafood.api.assembler.BasicAssembler;
import br.santosfyuri.algaworks.algafood.api.openapi.controller.ResponsibleRestaurantUserControllerOpenApi;
import br.santosfyuri.algaworks.algafood.api.representation.response.UserResponse;
import br.santosfyuri.algaworks.algafood.domain.model.Restaurant;
import br.santosfyuri.algaworks.algafood.domain.model.User;
import br.santosfyuri.algaworks.algafood.domain.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/restaurants/{restaurantId}/responsible-users")
public class ResponsibleRestaurantUserController implements ResponsibleRestaurantUserControllerOpenApi {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private BasicAssembler assembler;

    @GetMapping
    public CollectionModel<UserResponse> list(@PathVariable Long restaurantId) {
        Restaurant restaurante = restaurantService.findOrNull(restaurantId);
        return assembler.<User, UserResponse>get(getParameters(restaurantId))
                .toCollectionModel(restaurante.getResponsibleUsers());
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.asassociateResponsibleUser(restaurantId, userId);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.disassociateResponsibleUser(restaurantId, userId);
    }

    private AssemblerParameters<UserResponse> getParameters(Long ref) {
        return new AssemblerParameters<>(UserResponse.class, this.getClass(), discover ->
                discover.add(linkTo(methodOn(this.getClass()).list(ref)).withSelfRel()));
    }

}
