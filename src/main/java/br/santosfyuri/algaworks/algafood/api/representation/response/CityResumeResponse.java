package br.santosfyuri.algaworks.algafood.api.representation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation("Cities")
@Getter
@Setter
public class CityResumeResponse extends RepresentationModel<CityResumeResponse> {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Uberlândia")
    private String name;

    @Schema(example = "Minas Gerais")
    private String state;
}
