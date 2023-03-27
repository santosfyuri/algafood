package br.santosfyuri.algaworks.algafood.api.representation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionResponse {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "KITCHEN_CONSULT")
    private String name;

    @Schema(example = "Allows to consult kitchens")
    private String description;
}
