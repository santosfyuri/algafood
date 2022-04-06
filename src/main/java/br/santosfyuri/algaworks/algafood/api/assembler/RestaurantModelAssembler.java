package br.santosfyuri.algaworks.algafood.api.assembler;

import br.santosfyuri.algaworks.algafood.api.model.RestaurantModel;
import br.santosfyuri.algaworks.algafood.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

//TODO: refatorar para aceitar tipos genéricos
@Component
public class RestaurantModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public RestaurantModel toModel(Restaurant restaurant) {
        return modelMapper.map(restaurant, RestaurantModel.class);
    }

    public List<RestaurantModel> toCollectionModel(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
