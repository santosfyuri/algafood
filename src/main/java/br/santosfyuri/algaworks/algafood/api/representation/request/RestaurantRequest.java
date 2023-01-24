package br.santosfyuri.algaworks.algafood.api.representation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantRequest {

    @Schema(example = "Thai Gourmet")
    @NotBlank
    private String name;

    @Schema(example = "12.00")
    @NotNull
    @PositiveOrZero
    private BigDecimal deliveryFee;

    @Valid
    @NotNull
    private KitchenIdRequest kitchen;

    @Valid
    private AddressRequest address;

    private boolean active;
}
