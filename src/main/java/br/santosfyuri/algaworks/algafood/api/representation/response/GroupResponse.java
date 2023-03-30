package br.santosfyuri.algaworks.algafood.api.representation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class GroupResponse extends RepresentationModel<GroupResponse> {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Manager")
    private String name;
}
