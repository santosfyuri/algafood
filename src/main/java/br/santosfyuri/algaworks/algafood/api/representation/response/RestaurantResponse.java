package br.santosfyuri.algaworks.algafood.api.representation.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantResponse {

    private Long id;
    private String name;
    private BigDecimal deliveryFee;
    private KitchenResponse kitchen;
    private boolean active;
    private boolean open;
    private AddressResponse address;
}
