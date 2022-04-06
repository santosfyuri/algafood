package br.santosfyuri.algaworks.algafood.api.model.mixin;

import br.santosfyuri.algaworks.algafood.domain.model.State;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CityMixin {

    @JsonIgnoreProperties(value = "name", allowGetters = true)
    private State state;
}
