package br.santosfyuri.algaworks.algafood.api.openapi.controller;

import br.santosfyuri.algaworks.algafood.api.representation.response.PaymentMethodResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Restaurant payment methods")
public interface RestaurantPaymentMethodControllerOpenApi {

    @Operation(summary = "List all restaurant payment methods")
    List<PaymentMethodResponse> list(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId);

    @Operation(summary = "Disassociation of the restaurant with the payment method", responses = {
            @ApiResponse(responseCode = "204", description = "Disassociation done successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant or payment method not found")
    })
    void disassociate(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId,
                      @Parameter(description = "Payment method ID", example = "1", required = true) Long paymentMethod);


    @Operation(summary = "Association of the restaurant with the payment method", responses = {
            @ApiResponse(responseCode = "204", description = "Association done successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant or payment method not found")
    })
    void asassociate(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId,
                     @Parameter(description = "Payment method ID", example = "1", required = true) Long paymentMethod);
}
