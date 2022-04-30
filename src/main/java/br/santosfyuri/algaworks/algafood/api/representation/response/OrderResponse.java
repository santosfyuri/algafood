package br.santosfyuri.algaworks.algafood.api.representation.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponse {

    private String code;
    private BigDecimal subTotal;
    private BigDecimal deliveryFee;
    private BigDecimal total;
    private String status;
    private OffsetDateTime createdAt;
    private OffsetDateTime confirmationDate;
    private OffsetDateTime deliveryDate;
    private OffsetDateTime cancelationDate;
    private RestaurantResumeResponse restaurant;
    private UserResponse client;
    private PaymentMethodResponse paymentMethod;
    private AddressResponse deliveryAddress;
    private List<OrderItemResponse> items;
}
