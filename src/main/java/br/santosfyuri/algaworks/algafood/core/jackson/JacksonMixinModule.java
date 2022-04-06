package br.santosfyuri.algaworks.algafood.core.jackson;

import br.santosfyuri.algaworks.algafood.api.model.mixin.CityMixin;
import br.santosfyuri.algaworks.algafood.api.model.mixin.KitchenMixin;
import br.santosfyuri.algaworks.algafood.domain.model.City;
import br.santosfyuri.algaworks.algafood.domain.model.Kitchen;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(City.class, CityMixin.class);
        setMixInAnnotation(Kitchen.class, KitchenMixin.class);
    }
}
