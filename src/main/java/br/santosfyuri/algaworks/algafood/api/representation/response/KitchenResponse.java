package br.santosfyuri.algaworks.algafood.api.representation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation("kitchens")
@Getter
@Setter
public class KitchenResponse extends RepresentationModel<KitchenResponse> {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Brazilian")
    private String name;
}
