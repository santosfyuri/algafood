package br.santosfyuri.algaworks.algafood.api.model.input;

import br.santosfyuri.algaworks.algafood.api.model.input.CityIdInput;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddressInput {

    private String cep;

    private String street;

    private String number;

    private String complement;

    private String district;

    @Valid
    @NotNull
    private CityIdInput city;
}
