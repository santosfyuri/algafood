package br.santosfyuri.algaworks.algafood.api.controller;

import br.santosfyuri.algaworks.algafood.api.openapi.controller.StatisticsControllerOpenApi;
import br.santosfyuri.algaworks.algafood.domain.dto.DailySale;
import br.santosfyuri.algaworks.algafood.domain.filter.DailySaleFilter;
import br.santosfyuri.algaworks.algafood.domain.service.SaleQueryService;
import br.santosfyuri.algaworks.algafood.domain.service.SaleReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController implements StatisticsControllerOpenApi {

    @Autowired
    private SaleQueryService saleQueryService;

    @Autowired
    private SaleReportService saleReportService;

    @GetMapping(path = "/daily-sales", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DailySale> consultDailySaleJson(DailySaleFilter filter) {
        return saleQueryService.consultDailySale(filter);
    }

    @GetMapping(path = "/daily-sales", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> consultDailySalePdf(DailySaleFilter filter) {
        byte[] bytes = saleReportService.sendDailySale(filter);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=daily-sales.pdf");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(bytes);
    }
}
