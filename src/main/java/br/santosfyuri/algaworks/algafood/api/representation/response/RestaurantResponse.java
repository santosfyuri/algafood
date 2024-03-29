package br.santosfyuri.algaworks.algafood.api.representation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantResponse {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Thai Gourmet")
    private String name;

    @Schema(example = "12.00")
    private BigDecimal deliveryFee;

    private KitchenResponse kitchen;
    private boolean active;
    private boolean open;
    private AddressResponse address;
}
