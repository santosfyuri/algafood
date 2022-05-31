package br.santosfyuri.algaworks.algafood.domain.service;

import br.santosfyuri.algaworks.algafood.domain.model.Order;
import br.santosfyuri.algaworks.algafood.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderFlowService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void confirm(String orderCode) {
        Order order = orderService.findOrNull(orderCode);
        order.confirm();
        orderRepository.save(order);
    }

    @Transactional
    public void cancel(String orderCode) {
        Order order = orderService.findOrNull(orderCode);
        order.cancel();
        orderRepository.save(order);
    }

    @Transactional
    public void deliver(String orderCode) {
        Order order = orderService.findOrNull(orderCode);
        order.deliver();
    }
}
