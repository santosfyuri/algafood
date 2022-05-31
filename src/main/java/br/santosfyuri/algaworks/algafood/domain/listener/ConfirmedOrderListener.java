package br.santosfyuri.algaworks.algafood.domain.listener;

import br.santosfyuri.algaworks.algafood.domain.event.ConfirmedOrderEvent;
import br.santosfyuri.algaworks.algafood.domain.model.Order;
import br.santosfyuri.algaworks.algafood.domain.service.EmailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.event.TransactionalEventListener;

@Configuration
public class ConfirmedOrderListener {

    @Autowired
    private EmailSendService emailSendService;

    @TransactionalEventListener
    public void whenConfirmingOrder(ConfirmedOrderEvent event) {
        Order order = event.getOrder();
        EmailSendService.Message message = EmailSendService.Message.create()
                .subject(order.getRestaurant().getName() + "Pedido confirmado")
                .body("confirmed_order.html")
                .variable("order", order)
                .recipient(order.getClient().getEmail())
                .build();
        emailSendService.send(message);
    }
}
