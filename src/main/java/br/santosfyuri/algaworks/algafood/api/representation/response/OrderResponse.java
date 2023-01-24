package br.santosfyuri.algaworks.algafood.api.representation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponse {

    @Schema(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
    private String code;

    @Schema(example = "298.90")
    private BigDecimal subTotal;

    @Schema(example = "10.00")
    private BigDecimal deliveryFee;

    @Schema(example = "308.90")
    private BigDecimal total;

    @Schema(example = "CREATED")
    private String status;

    @Schema(example = "2022-12-01T20:34:04Z")
    private OffsetDateTime createdAt;

    @Schema(example = "2022-12-01T20:35:10Z")
    private OffsetDateTime confirmationDate;

    @Schema(example = "2022-12-01T20:55:30Z")
    private OffsetDateTime deliveryDate;

    @Schema(example = "2022-12-01T20:35:00Z")
    private OffsetDateTime cancelationDate;

    private RestaurantResumeResponse restaurant;

    private UserResponse client;

    private PaymentMethodResponse paymentMethod;

    private AddressResponse deliveryAddress;

    private List<OrderItemResponse> items;
}
