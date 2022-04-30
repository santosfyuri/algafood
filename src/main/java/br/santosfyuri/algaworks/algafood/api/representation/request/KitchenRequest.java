package br.santosfyuri.algaworks.algafood.api.representation.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class KitchenRequest {

    @NotBlank
    private String name;
}
