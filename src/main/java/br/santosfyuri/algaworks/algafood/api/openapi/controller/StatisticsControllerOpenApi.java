package br.santosfyuri.algaworks.algafood.api.openapi.controller;

import br.santosfyuri.algaworks.algafood.domain.dto.DailySale;
import br.santosfyuri.algaworks.algafood.domain.filter.DailySaleFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StatisticsControllerOpenApi {

    @Operation(summary = "Consulta estatísticas de vendas diárias",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "restaurantId", description = "Restaurant ID", example = "1", schema = @Schema(type = "integer")),
                    @Parameter(in = ParameterIn.QUERY, name = "createdAtBegin", description = "Order creation start date", example = "2019-12-01T00:00:00Z", schema = @Schema(type = "string", format = "date-time")),
                    @Parameter(in = ParameterIn.QUERY, name = "createdAtEnd", description = "Order creation end date", example = "2019-12-02T23:59:59Z", schema = @Schema(type = "string", format = "date-time"))},
            responses = {
                    @ApiResponse(responseCode = "200", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DailySale.class))),
                            @Content(mediaType = "application/pdf", schema = @Schema(type = "string", format = "binary"))})
            }
    )
    List<DailySale> consultDailySaleJson(@Parameter(hidden = true) DailySaleFilter filter);

    @Operation(hidden = true)
    ResponseEntity<byte[]> consultDailySalePdf(DailySaleFilter filter);

}
