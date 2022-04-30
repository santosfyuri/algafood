package br.santosfyuri.algaworks.algafood.api.controller;

import br.santosfyuri.algaworks.algafood.domain.service.OrderFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders/{orderCode}")
public class OrderFlowController {

    @Autowired
    private OrderFlowService orderFlowService;

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void add(@PathVariable String orderCode) {
        orderFlowService.confirm(orderCode);
    }

    @PutMapping("/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable String orderCode) {
        orderFlowService.cancel(orderCode);
    }

    @PutMapping("/deliver")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deliver(@PathVariable String orderCode) {
        orderFlowService.deliver(orderCode);
    }
}
