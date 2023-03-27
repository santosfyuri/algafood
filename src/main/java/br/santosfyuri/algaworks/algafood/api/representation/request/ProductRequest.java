package br.santosfyuri.algaworks.algafood.api.representation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequest {

    @NotBlank
    @Schema(example = "Espetinho de Cupim", required = true)
    private String name;

    @NotBlank
    @Schema(example = "Acompanha farinha, aipim e salada de batata (maionese)", required = true)
    private String description;

    @NotNull
    @PositiveOrZero
    @Schema(example = "12.50", required = true)
    private BigDecimal price;

    @NotNull
    @Schema(example = "true", required = true)
    private boolean active;
}
