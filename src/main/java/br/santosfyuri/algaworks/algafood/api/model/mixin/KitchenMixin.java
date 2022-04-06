package br.santosfyuri.algaworks.algafood.api.model.mixin;

import br.santosfyuri.algaworks.algafood.domain.model.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class KitchenMixin {

    @JsonIgnore
    private List<Restaurant> restaurants;
}
