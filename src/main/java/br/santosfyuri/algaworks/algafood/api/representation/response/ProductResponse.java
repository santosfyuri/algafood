package br.santosfyuri.algaworks.algafood.api.representation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponse {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Espetinho de Cupim")
    private String name;

    @Schema(example = "Acompanha farinha, aipim e salada de batata (maionese)")
    private String description;

    @Schema(example = "12.50")
    private BigDecimal price;

    @Schema(example = "true")
    private boolean active;
}
