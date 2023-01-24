package br.santosfyuri.algaworks.algafood.infrastructure.service.report;

import br.santosfyuri.algaworks.algafood.domain.dto.DailySale;
import br.santosfyuri.algaworks.algafood.domain.filter.DailySaleFilter;
import br.santosfyuri.algaworks.algafood.domain.service.SaleQueryService;
import br.santosfyuri.algaworks.algafood.domain.service.SaleReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class PdfSaleReportService implements SaleReportService {

    @Autowired
    private SaleQueryService saleQueryService;

    @Override
    public byte[] sendDailySale(DailySaleFilter filter) {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/reports/daily-sales.jasper");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("REPORT_LOCALE", new Locale("pt", "BR"));

            List<DailySale> sailySales = saleQueryService.consultDailySale(filter);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(sailySales);
            JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException exception) {
            throw new ReportException("Não foi possível emitir o relatório de vendas diárias", exception);
        }
    }
}
