package br.santosfyuri.algaworks.algafood.core.modelmapper;

import br.santosfyuri.algaworks.algafood.api.representation.request.OrderItemRequest;
import br.santosfyuri.algaworks.algafood.api.representation.request.RestaurantRequest;
import br.santosfyuri.algaworks.algafood.api.representation.response.AddressResponse;
import br.santosfyuri.algaworks.algafood.domain.model.Address;
import br.santosfyuri.algaworks.algafood.domain.model.OrderItem;
import br.santosfyuri.algaworks.algafood.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(RestaurantRequest.class, Restaurant.class)
                .addMapping(RestaurantRequest::getAddress, Restaurant::setAddress);
        modelMapper.createTypeMap(Address.class, AddressResponse.class).<String>addMapping(
                obj -> obj.getCity().getState().getName(),
                (dest, value) -> dest.getCity().setState(value));
        modelMapper.createTypeMap(OrderItemRequest.class, OrderItem.class)
                .addMappings(mapper -> mapper.skip(OrderItem::setId));
        return modelMapper;
    }
}
