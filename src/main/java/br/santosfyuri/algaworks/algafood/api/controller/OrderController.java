package br.santosfyuri.algaworks.algafood.api.controller;

import br.santosfyuri.algaworks.algafood.api.assembler.AssemblerParameters;
import br.santosfyuri.algaworks.algafood.api.assembler.BasicAssembler;
import br.santosfyuri.algaworks.algafood.api.assembler.BasicDisassembler;
import br.santosfyuri.algaworks.algafood.api.openapi.controller.OrderControllerOpenApi;
import br.santosfyuri.algaworks.algafood.api.representation.request.OrderRequest;
import br.santosfyuri.algaworks.algafood.api.representation.response.OrderResponse;
import br.santosfyuri.algaworks.algafood.api.representation.response.OrderResumeResponse;
import br.santosfyuri.algaworks.algafood.domain.exception.BusinessException;
import br.santosfyuri.algaworks.algafood.domain.exception.EntityNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.filter.OrderFilter;
import br.santosfyuri.algaworks.algafood.domain.model.Order;
import br.santosfyuri.algaworks.algafood.domain.model.User;
import br.santosfyuri.algaworks.algafood.domain.repository.OrderRepository;
import br.santosfyuri.algaworks.algafood.domain.service.OrderService;
import br.santosfyuri.algaworks.algafood.infrastructure.repository.spec.OrderSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static br.santosfyuri.algaworks.algafood.api.helpers.AlgaLinks.*;

@RestController
@RequestMapping(value = "/orders")
public class OrderController implements OrderControllerOpenApi {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BasicAssembler assembler;

    @Autowired
    private BasicDisassembler disassembler;

    @Autowired
    private PagedResourcesAssembler<Order> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<OrderResumeResponse> search(OrderFilter orderFilter, @PageableDefault(size = 10) Pageable pageable) {
        Page<Order> ordersPage = orderRepository.findAll(OrderSpecs.usingFilter(orderFilter), pageable);
        return pagedResourcesAssembler.toModel(ordersPage, assembler.get(getOrderResumeParameters()));
    }

    @GetMapping("/{orderCode}")
    public OrderResumeResponse find(@PathVariable String orderCode) {
        Order order = orderService.findOrNull(orderCode);
        return assembler.<Order, OrderResumeResponse>get(getOrderResumeParameters()).toModel(order);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse add(@Valid @RequestBody OrderRequest order) {
        try {
            Order newOrder = disassembler.<Order, OrderRequest>get(Order.class).representationToEntity(order);

            newOrder.setClient(new User());
            newOrder.getClient().setId(1L);

            newOrder = orderService.send(newOrder);

            return assembler.<Order, OrderResponse>get(getOrderParameters()).toModel(newOrder);
        } catch (EntityNotFoundException exception) {
            throw new BusinessException(exception.getMessage(), exception);
        }
    }

    private AssemblerParameters<OrderResponse> getOrderParameters() {
        return new AssemblerParameters<>(OrderResponse.class, this.getClass(), discover -> {
            discover.add(linkToOrder());
            discover.add(linkToOrder(discover.getCode()));
            discover.add(linkToAddOrder(discover.getCode()));
            discover.add(linkToCancelOrder(discover.getCode()));
            discover.add(linkToDeliverOrder(discover.getCode()));
            discover.getClient().add(linkToClient(discover.getClient().getId()));
            discover.getRestaurant().add(linkToRestaurant(discover.getRestaurant().getId()));
            discover.getPaymentMethod().add(linkToPaymentMethod(discover.getPaymentMethod().getId()));
            discover.getDeliveryAddress().getCity().add(linkToCity(discover.getDeliveryAddress().getCity().getId()));
            discover.getItems().forEach(item -> {
                item.add(linkToOrderItem(discover.getRestaurant().getId(), item.getProductId()));
            });
        });
    }

    private AssemblerParameters<OrderResumeResponse> getOrderResumeParameters() {
        return new AssemblerParameters<>(OrderResumeResponse.class, this.getClass(), discover -> {
            discover.add(linkToOrder());
            discover.add(linkToOrder(discover.getCode()));
            discover.add(linkToAddOrder(discover.getCode()));
            discover.add(linkToCancelOrder(discover.getCode()));
            discover.add(linkToDeliverOrder(discover.getCode()));
            discover.getUser().add(linkToClient(discover.getUser().getId()));
            discover.getRestaurant().add(linkToRestaurant(discover.getRestaurant().getId()));
        });
    }
}
