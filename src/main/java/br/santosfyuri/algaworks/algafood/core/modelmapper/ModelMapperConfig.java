package br.santosfyuri.algaworks.algafood.core.modelmapper;

import br.santosfyuri.algaworks.algafood.api.model.input.RestaurantInput;
import br.santosfyuri.algaworks.algafood.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(RestaurantInput.class, Restaurant.class)
                .addMapping(RestaurantInput::getAddress, Restaurant::setAddress);
        return modelMapper;
    }
}
