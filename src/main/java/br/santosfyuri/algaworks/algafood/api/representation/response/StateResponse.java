package br.santosfyuri.algaworks.algafood.api.representation.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class StateResponse extends RepresentationModel<StateResponse> {

    private Long id;
    private String name;
}
