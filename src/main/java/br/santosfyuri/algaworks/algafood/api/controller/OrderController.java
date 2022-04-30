package br.santosfyuri.algaworks.algafood.api.controller;

import br.santosfyuri.algaworks.algafood.api.assembler.BasicAssembler;
import br.santosfyuri.algaworks.algafood.api.assembler.BasicDisassembler;
import br.santosfyuri.algaworks.algafood.api.representation.request.OrderRequest;
import br.santosfyuri.algaworks.algafood.api.representation.response.OrderResponse;
import br.santosfyuri.algaworks.algafood.api.representation.response.OrderResumeResponse;
import br.santosfyuri.algaworks.algafood.domain.exception.BusinessException;
import br.santosfyuri.algaworks.algafood.domain.exception.EntityNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.model.Order;
import br.santosfyuri.algaworks.algafood.domain.model.User;
import br.santosfyuri.algaworks.algafood.domain.repository.OrderRepository;
import br.santosfyuri.algaworks.algafood.domain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BasicAssembler assembler;

    @Autowired
    private BasicDisassembler disassembler;

    @GetMapping
    public List<OrderResumeResponse> list() {
        List<Order> orders = orderRepository.findAll();
        return assembler.<Order, OrderResumeResponse>get(OrderResumeResponse.class).entityToRepresentation(orders);
    }

    @GetMapping("/{orderCode}")
    public OrderResumeResponse find(@PathVariable String orderCode) {
        Order order = orderService.findOrNull(orderCode);
        return assembler.<Order, OrderResumeResponse>get(OrderResumeResponse.class).entityToRepresentation(order);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse add(@Valid @RequestBody OrderRequest order) {
        try {
            Order newOrder = disassembler.<Order, OrderRequest>get(Order.class).representationToEntity(order);

            newOrder.setClient(new User());
            newOrder.getClient().setId(1L);

            newOrder = orderService.send(newOrder);

            return assembler.<Order, OrderResponse>get(OrderResponse.class).entityToRepresentation(newOrder);
        } catch (EntityNotFoundException exception) {
            throw new BusinessException(exception.getMessage(), exception);
        }
    }
}
