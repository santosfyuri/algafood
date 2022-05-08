package br.santosfyuri.algaworks.algafood.domain.service;

import br.santosfyuri.algaworks.algafood.domain.filter.DailySaleFilter;

public interface SaleReportService {

    byte[] sendDailySale(DailySaleFilter filter);
}
