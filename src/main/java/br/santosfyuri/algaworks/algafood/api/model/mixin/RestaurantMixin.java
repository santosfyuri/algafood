package br.santosfyuri.algaworks.algafood.api.model.mixin;

import br.santosfyuri.algaworks.algafood.domain.model.Address;
import br.santosfyuri.algaworks.algafood.domain.model.Kitchen;
import br.santosfyuri.algaworks.algafood.domain.model.PaymentMethod;
import br.santosfyuri.algaworks.algafood.domain.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.OffsetDateTime;
import java.util.List;

public class RestaurantMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Kitchen kitchen;

    @JsonIgnore
    private Address address;

    @JsonIgnore
    private OffsetDateTime createdAt;

    @JsonIgnore
    private OffsetDateTime updatedAt;

    @JsonIgnore
    private List<PaymentMethod> paymentMethods;

    @JsonIgnore
    private List<Product> products;
}
