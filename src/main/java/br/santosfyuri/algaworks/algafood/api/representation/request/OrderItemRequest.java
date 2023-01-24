package br.santosfyuri.algaworks.algafood.api.representation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class OrderItemRequest {

    @Schema(example = "1")
    @NotNull
    private Long productId;

    @Schema(example = "2")
    @NotNull
    @PositiveOrZero
    private Integer quantity;

    @Schema(example = "little spicy, please")
    private String note;
}
