package br.santosfyuri.algaworks.algafood.api.representation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupResponse {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Manager")
    private String name;
}
