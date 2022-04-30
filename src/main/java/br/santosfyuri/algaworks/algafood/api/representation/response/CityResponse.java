package br.santosfyuri.algaworks.algafood.api.representation.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityResponse {

    private Long id;
    private String name;
    private StateResponse state;
}
