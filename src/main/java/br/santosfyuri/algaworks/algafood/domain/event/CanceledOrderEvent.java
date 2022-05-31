package br.santosfyuri.algaworks.algafood.domain.event;

import br.santosfyuri.algaworks.algafood.domain.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CanceledOrderEvent {

    private Order order;
}
