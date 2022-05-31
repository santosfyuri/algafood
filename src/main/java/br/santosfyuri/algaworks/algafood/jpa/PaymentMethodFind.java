package br.santosfyuri.algaworks.algafood.jpa;

import br.santosfyuri.algaworks.algafood.AlgaFoodApplication;
import br.santosfyuri.algaworks.algafood.domain.model.PaymentMethod;
import br.santosfyuri.algaworks.algafood.domain.repository.PaymentMethodRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class PaymentMethodFind {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgaFoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        PaymentMethodRepository paymentMethodRepository = applicationContext.getBean(PaymentMethodRepository.class);
        List<PaymentMethod> paymentMethods = paymentMethodRepository.findAll();
        paymentMethods.forEach(r -> {
            System.out.printf("%d - %s\n",
                    r.getId(), r.getDescription());
        });
    }
}
