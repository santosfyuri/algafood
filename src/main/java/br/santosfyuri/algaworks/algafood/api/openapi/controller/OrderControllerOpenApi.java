package br.santosfyuri.algaworks.algafood.api.openapi.controller;

import br.santosfyuri.algaworks.algafood.api.representation.request.OrderRequest;
import br.santosfyuri.algaworks.algafood.api.representation.response.OrderResponse;
import br.santosfyuri.algaworks.algafood.api.representation.response.OrderResumeResponse;
import br.santosfyuri.algaworks.algafood.core.openapi.PageableParameter;
import br.santosfyuri.algaworks.algafood.domain.filter.OrderFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Tag(name = "Orders")
public interface OrderControllerOpenApi {

    @Operation(
            summary = "Search for the orders",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "clienteId",
                            description = "Client ID",
                            example = "1", schema = @Schema(type = "integer")),
                    @Parameter(in = ParameterIn.QUERY, name = "restauranteId",
                            description = "Restaurant ID",
                            example = "1", schema = @Schema(type = "integer")),
                    @Parameter(in = ParameterIn.QUERY, name = "createdAtBegin",
                            description = "Start datetime",
                            example = "2019-12-01T00:00:00Z", schema = @Schema(type = "string", format = "date-time")),
                    @Parameter(in = ParameterIn.QUERY, name = "createdAtEnd",
                            description = "End datetime",
                            example = "2019-12-02T23:59:59Z", schema = @Schema(type = "string", format = "date-time"))
            }
    )
    @PageableParameter
    Page<OrderResumeResponse> search(@Parameter(hidden = true) OrderFilter orderFilter,
                                     @Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Search an order by code", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
    })
    OrderResumeResponse find(@Parameter(description = "Order code", example = "04813f77-79b5-11ec-9a17-0242ac1b0002", required = true) String orderCode);

    @Operation(summary = "Register an order", responses = {
            @ApiResponse(responseCode = "201", description = "Pedido registrado"),
    })
    OrderResponse add(@RequestBody(description = "New order representation", required = true) OrderRequest order);
}
