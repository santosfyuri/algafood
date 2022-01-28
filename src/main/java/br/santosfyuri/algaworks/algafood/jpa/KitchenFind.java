package br.santosfyuri.algaworks.algafood.jpa;

import br.santosfyuri.algaworks.algafood.AlgaFoodApplication;
import br.santosfyuri.algaworks.algafood.domain.model.Kitchen;
import br.santosfyuri.algaworks.algafood.domain.repository.KitchenRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class KitchenFind {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgaFoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenRepository kitchenRepository = applicationContext.getBean(KitchenRepository.class);
        List<Kitchen> kitchens = kitchenRepository.list();
        kitchens.forEach(r -> {
            System.out.printf("%d - %s\n",
                    r.getId(), r.getName());
        });
    }
}
