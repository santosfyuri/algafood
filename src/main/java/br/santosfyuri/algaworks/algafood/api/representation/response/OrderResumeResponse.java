package br.santosfyuri.algaworks.algafood.api.representation.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class OrderResumeResponse {

    private String code;
    private BigDecimal subTotal;
    private BigDecimal deliveryFee;
    private BigDecimal total;
    private String status;
    private OffsetDateTime createdAt;
    private RestaurantResumeResponse restaurant;
    private UserResponse user;
}
