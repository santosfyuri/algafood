package br.santosfyuri.algaworks.algafood.api.helpers;

import br.santosfyuri.algaworks.algafood.api.controller.*;
import lombok.experimental.UtilityClass;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@UtilityClass
public class AlgaLinks {

    public static Link linkToKitchen(Long id) {
        return linkTo(methodOn(KitchenController.class).find(id)).withSelfRel();
    }

    public static Link linkToKitchen(String rel) {
        return linkTo(KitchenController.class).withRel(rel);
    }

    public static Link linkToOrder() {
        TemplateVariables pageVariables = new TemplateVariables(
                new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM)
        );

        TemplateVariables filterVariables = new TemplateVariables(
                new TemplateVariable("clientId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("restaurantId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("createdAtBegin", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("createdAtEnd", TemplateVariable.VariableType.REQUEST_PARAM)
        );
        return Link.of(UriTemplate.of(linkTo(OrderController.class).toUri().toString(),
                pageVariables.concat(filterVariables)), "orders");
    }

    public static Link linkToOrder(String ref) {
        return linkTo(methodOn(OrderController.class).find(ref)).withSelfRel();
    }

    public static Link linkToPaymentMethod(Long id) {
        return linkTo(methodOn(PaymentMethodController.class).find(id, null)).withRel("payment-methods");
    }

    public static Link linkToCity(Long id) {
        return linkTo(methodOn(CityController.class).find(id)).withRel("cities");
    }

    public static Link linkToOrderItem(Long resId, Long itemId) {
        return linkTo(methodOn(RestaurantProductController.class).find(resId, itemId)).withRel("product");
    }

    public static Link linkToClient(Long id) {
        return linkTo(methodOn(UserController.class).find(id)).withRel("clients");
    }

    public static Link linkToRestaurant(Long id) {
        return linkTo(methodOn(RestaurantController.class).find(id)).withRel("restaurants");
    }

    public static Link linkToState() {
        return linkTo(StateController.class).withRel("states");
    }

    public static Link linkToState(Long id) {
        return linkTo(methodOn(StateController.class).find(id)).withSelfRel();
    }

    public static Link linkToUser(Long id) {
        return linkTo(methodOn(UserController.class).find(id)).withSelfRel();
    }

    public static Link linkToUser() {
        return linkTo(UserController.class).withRel("users");
    }

    public static Link linkToUserGroups() {
        return linkTo(methodOn(UserController.class).list()).withRel("users-groups");
    }

    public static Link linkToGroup(Long id) {
        return linkTo(methodOn(GroupController.class).find(id)).withSelfRel();
    }

    public static Link linkToAddOrder(String ref) {
        return linkTo(methodOn(OrderFlowController.class).add(ref)).withRel("add");
    }

    public static Link linkToCancelOrder(String ref) {
        return linkTo(methodOn(OrderFlowController.class).cancel(ref)).withRel("cancel");
    }

    public static Link linkToDeliverOrder(String ref) {
        return linkTo(methodOn(OrderFlowController.class).deliver(ref)).withRel("deliver");
    }

}
