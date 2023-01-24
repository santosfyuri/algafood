package br.santosfyuri.algaworks.algafood.api.representation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddressRequest {

    @Schema(example = "38400-000")
    @NotBlank
    private String cep;

    @Schema(example = "Floriano Peixoto")
    @NotBlank
    private String street;

    @Schema(example = "\"1500\"")
    @NotBlank
    private String number;

    @Schema(example = "Apto 901")
    private String complement;

    @Schema(example = "Centro")
    @NotBlank
    private String district;

    @Valid
    @NotNull
    private CityIdRequest city;
}
