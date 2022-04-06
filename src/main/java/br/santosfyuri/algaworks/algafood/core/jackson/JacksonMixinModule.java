package br.santosfyuri.algaworks.algafood.core.jackson;

import br.santosfyuri.algaworks.algafood.api.model.mixin.CityMixin;
import br.santosfyuri.algaworks.algafood.api.model.mixin.KitchenMixin;
import br.santosfyuri.algaworks.algafood.api.model.mixin.RestaurantMixin;
import br.santosfyuri.algaworks.algafood.domain.model.City;
import br.santosfyuri.algaworks.algafood.domain.model.Kitchen;
import br.santosfyuri.algaworks.algafood.domain.model.Restaurant;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurant.class, RestaurantMixin.class);
        setMixInAnnotation(City.class, CityMixin.class);
        setMixInAnnotation(Kitchen.class, KitchenMixin.class);
    }
}
