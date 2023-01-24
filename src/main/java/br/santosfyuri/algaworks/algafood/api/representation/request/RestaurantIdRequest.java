package br.santosfyuri.algaworks.algafood.api.representation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RestaurantIdRequest {

    @Schema(example = "1")
    @NotNull
    private Long id;
}
