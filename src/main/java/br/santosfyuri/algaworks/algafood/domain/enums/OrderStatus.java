package br.santosfyuri.algaworks.algafood.domain.enums;

import java.util.Arrays;
import java.util.List;

public enum OrderStatus {

    CRIADO("Criado"),
    CONFIRMADO("Confirmado", CRIADO),
    ENTREGUE("Entregue", CONFIRMADO),
    CANCELADO("Cancelado", CRIADO);

    private String description;
    private List<OrderStatus> previousStatus;

    OrderStatus(final String description, final OrderStatus... previousStatus) {
        this.description = description;
        this.previousStatus = Arrays.asList(previousStatus);
    }

    public String getDescription() {
        return this.description;
    }

    public boolean cannotChangeTo(OrderStatus newStatus) {
        return !newStatus.previousStatus.contains(this);
    }
}
