package br.santosfyuri.algaworks.algafood.domain.service;

import br.santosfyuri.algaworks.algafood.domain.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderFlowService {

    @Autowired
    private OrderService orderService;

    @Transactional
    public void confirm(String orderCode) {
        Order order = orderService.findOrNull(orderCode);
        order.confirm();
    }

    @Transactional
    public void cancel(String orderCode) {
        Order order = orderService.findOrNull(orderCode);
        order.cancel();
    }

    @Transactional
    public void deliver(String orderCode) {
        Order order = orderService.findOrNull(orderCode);
        order.deliver();
    }
}
