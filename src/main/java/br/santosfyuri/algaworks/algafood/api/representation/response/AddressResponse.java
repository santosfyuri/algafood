package br.santosfyuri.algaworks.algafood.api.representation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse {

    @Schema(example = "38400-000")
    private String cep;

    @Schema(example = "Rua Floriano Peixoto")
    private String street;

    @Schema(example = "\"1500\"")
    private String number;

    @Schema(example = "Apto 901")
    private String complement;

    @Schema(example = "Centro")
    private String district;

    private CityResumeResponse city;
}
