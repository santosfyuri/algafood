package br.santosfyuri.algaworks.algafood.jpa;

import br.santosfyuri.algaworks.algafood.AlgaFoodApplication;
import br.santosfyuri.algaworks.algafood.domain.model.City;
import br.santosfyuri.algaworks.algafood.domain.repository.CityRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class CityFind {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgaFoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CityRepository cityRepository = applicationContext.getBean(CityRepository.class);
        List<City> cities = cityRepository.list();
        cities.forEach(r -> {
            System.out.printf("%d - %s - %s\n",
                    r.getId(), r.getName(), r.getState().getName());
        });
    }
}
