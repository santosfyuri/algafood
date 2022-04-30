package br.santosfyuri.algaworks.algafood.api.controller;

import br.santosfyuri.algaworks.algafood.api.assembler.BasicAssembler;
import br.santosfyuri.algaworks.algafood.api.representation.response.PaymentMethodResponse;
import br.santosfyuri.algaworks.algafood.domain.model.PaymentMethod;
import br.santosfyuri.algaworks.algafood.domain.model.Restaurant;
import br.santosfyuri.algaworks.algafood.domain.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/payment-methods")
public class RestaurantPaymentMethodController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private BasicAssembler assembler;

    @GetMapping
    public List<PaymentMethodResponse> list(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.findOrNull(restaurantId);

        return assembler.<PaymentMethod, PaymentMethodResponse>get(PaymentMethodResponse.class)
                .entityToRepresentation(restaurant.getPaymentMethods());
    }

    @DeleteMapping("/{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId) {
        restaurantService.disassociatePaymentMethod(restaurantId, paymentMethodId);
    }

    @PutMapping("/{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void asassociate(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId) {
        restaurantService.asassociatePaymentMethod(restaurantId, paymentMethodId);
    }
}
