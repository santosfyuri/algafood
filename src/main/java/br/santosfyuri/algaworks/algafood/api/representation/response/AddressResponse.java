package br.santosfyuri.algaworks.algafood.api.representation.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse {

    private String cep;
    private String street;
    private String number;
    private String complement;
    private String district;
    private CityResumeResponse city;
}
