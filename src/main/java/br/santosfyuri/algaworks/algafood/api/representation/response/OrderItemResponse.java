package br.santosfyuri.algaworks.algafood.api.representation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemResponse {

    @Schema(example = "1")
    private Long productId;

    @Schema(example = "Pork with sweet and sour sauce")
    private String productName;

    @Schema(example = "2")
    private Integer quantity;

    @Schema(example = "78.90")
    private BigDecimal unitPrice;

    @Schema(example = "157.80")
    private BigDecimal total;

    @Schema(example = "Little spicy, please")
    private String note;
}
