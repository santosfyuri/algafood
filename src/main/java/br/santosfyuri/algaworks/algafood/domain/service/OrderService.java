package br.santosfyuri.algaworks.algafood.domain.service;

import br.santosfyuri.algaworks.algafood.domain.exception.BusinessException;
import br.santosfyuri.algaworks.algafood.domain.exception.OrderNotFoundException;
import br.santosfyuri.algaworks.algafood.domain.model.*;
import br.santosfyuri.algaworks.algafood.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CityService cityService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentMethodService paymentMethodService;

    public Order findOrNull(String orderCode) {
        return orderRepository.findByCode(orderCode)
                .orElseThrow(() -> new OrderNotFoundException(orderCode));
    }

    @Transactional
    public Order send(Order order) {
        validateOrder(order);
        validateItems(order);

        order.setDeliveryFee(order.getRestaurant().getDeliveryFee());
        order.calculateTotal();

        return orderRepository.save(order);
    }

    private void validateOrder(Order order) {
        City city = cityService.findOrNull(order.getDeliveryAddress().getCity().getId());
        User client = userService.findOrNull(order.getClient().getId());
        Restaurant restaurant = restaurantService.findOrNull(order.getRestaurant().getId());
        PaymentMethod paymentMethod = paymentMethodService.findOrNull(order.getPaymentMethod().getId());

        order.getDeliveryAddress().setCity(city);
        order.setClient(client);
        order.setRestaurant(restaurant);
        order.setPaymentMethod(paymentMethod);

        if (restaurant.notAcceptsPaymentMethod(paymentMethod)) {
            throw new BusinessException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
                    paymentMethod.getDescription()));
        }
    }

    private void validateItems(Order order) {
        order.getItens().forEach(item -> {
            Product product = productService.findOrNull(
                    order.getRestaurant().getId(), item.getProduct().getId());

            item.setOrder(order);
            item.setProduct(product);
            item.setUnitPrice(product.getPrice());
        });
    }
}
