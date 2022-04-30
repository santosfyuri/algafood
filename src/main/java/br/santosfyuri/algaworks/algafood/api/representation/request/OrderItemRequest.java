package br.santosfyuri.algaworks.algafood.api.representation.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class OrderItemRequest {

    @NotNull
    private Long productId;

    @NotNull
    @PositiveOrZero
    private Integer quantity;

    private String note;
}
