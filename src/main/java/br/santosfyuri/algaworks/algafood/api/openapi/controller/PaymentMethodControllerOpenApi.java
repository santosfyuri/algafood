package br.santosfyuri.algaworks.algafood.api.openapi.controller;

import br.santosfyuri.algaworks.algafood.api.representation.request.PaymentMethodRequest;
import br.santosfyuri.algaworks.algafood.api.representation.response.PaymentMethodResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.List;

@Tag(name = "Payment methods")
public interface PaymentMethodControllerOpenApi {

    @Operation(summary = "List all payment methods")
    ResponseEntity<List<PaymentMethodResponse>> list(@Parameter(hidden = true) ServletWebRequest request);

    @Operation(summary = "Search a payment method by ID", responses = {
            @ApiResponse(responseCode = "400", description = "Payment method ID invalid"),
            @ApiResponse(responseCode = "404", description = "Payment method not found")
    })
    ResponseEntity<PaymentMethodResponse> find(@Parameter(description = "Payment method ID", example = "1", required = true) Long id,
                                               @Parameter(hidden = true) ServletWebRequest request);


    @Operation(summary = "Register a payment method", responses = {
            @ApiResponse(responseCode = "201", description = "Registered payment method")
    })
    PaymentMethodResponse save(@RequestBody(description = "New payment method representation", required = true) PaymentMethodRequest input);

    @Operation(summary = "Update a payment method by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Updated payment method"),
            @ApiResponse(responseCode = "404", description = "Payment method not found")
    })
    PaymentMethodResponse update(@Parameter(description = "Payment method ID", example = "1", required = true) Long id,
                                 @RequestBody(description = "New payment method representation", required = true) PaymentMethodRequest input);

    void delete(@Parameter(description = "City ID", example = "1", required = true) Long id);
}
