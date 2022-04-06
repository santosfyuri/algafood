package br.santosfyuri.algaworks.algafood.api.assembler;

import br.santosfyuri.algaworks.algafood.api.model.input.RestaurantInput;
import br.santosfyuri.algaworks.algafood.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//TODO: refatorar para aceitar tipos genéricos
@Component
public class RestaurantInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurant toDomainObject(RestaurantInput input) {
        return modelMapper.map(input, Restaurant.class);
    }
}
