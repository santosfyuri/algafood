package br.santosfyuri.algaworks.algafood.domain.listener;

import br.santosfyuri.algaworks.algafood.domain.event.CanceledOrderEvent;
import br.santosfyuri.algaworks.algafood.domain.model.Order;
import br.santosfyuri.algaworks.algafood.domain.service.EmailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.event.TransactionalEventListener;

@Configuration
public class CanceledOrderListener {

    @Autowired
    private EmailSendService emailSendService;

    @TransactionalEventListener
    public void whenCanceledOrder(CanceledOrderEvent event) {
        Order order = event.getOrder();

        var mensagem = EmailSendService.Message.create()
                .subject(order.getRestaurant().getName() + " - Pedido cancelado")
                .body("canceled_order.html")
                .variable("pedido", order)
                .recipient(order.getClient().getEmail())
                .build();

        emailSendService.send(mensagem);
    }
}
