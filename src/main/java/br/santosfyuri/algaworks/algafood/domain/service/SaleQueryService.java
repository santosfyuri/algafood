package br.santosfyuri.algaworks.algafood.domain.service;

import br.santosfyuri.algaworks.algafood.domain.dto.DailySale;
import br.santosfyuri.algaworks.algafood.domain.filter.DailySaleFilter;

import java.util.List;

public interface SaleQueryService {

    List<DailySale> consultDailySale(DailySaleFilter dailySaleFilter);
}
