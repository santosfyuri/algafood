package br.santosfyuri.algaworks.algafood.api.representation.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class OrderRequest {

    @Valid
    @NotNull
    private RestaurantIdRequest restaurant;

    @Valid
    @NotNull
    private AddressRequest deliveryAddress;

    @Valid
    @NotNull
    private PaymentMethodIdRequest paymentMethod;

    @Valid
    @Size(min = 1)
    @NotNull
    private List<OrderItemRequest> items;
}
