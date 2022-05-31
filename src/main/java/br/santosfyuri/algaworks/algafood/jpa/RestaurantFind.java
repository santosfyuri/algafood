package br.santosfyuri.algaworks.algafood.jpa;

import br.santosfyuri.algaworks.algafood.AlgaFoodApplication;
import br.santosfyuri.algaworks.algafood.domain.model.Restaurant;
import br.santosfyuri.algaworks.algafood.domain.repository.RestaurantRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class RestaurantFind {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgaFoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestaurantRepository restaurantRepository = applicationContext.getBean(RestaurantRepository.class);
        List<Restaurant> restaurants = restaurantRepository.findAll();
        restaurants.forEach(r -> {
            System.out.printf("%s - %f - %s\n",
                    r.getName(), r.getDeliveryFee(), r.getKitchen().getName());
        });
    }
}
