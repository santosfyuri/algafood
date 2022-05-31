package br.santosfyuri.algaworks.algafood.domain.model;

import br.santosfyuri.algaworks.algafood.domain.enums.OrderStatus;
import br.santosfyuri.algaworks.algafood.domain.event.CanceledOrderEvent;
import br.santosfyuri.algaworks.algafood.domain.event.ConfirmedOrderEvent;
import br.santosfyuri.algaworks.algafood.domain.exception.BusinessException;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static br.santosfyuri.algaworks.algafood.domain.constants.DatabaseConstants.SCHEMA;

@Getter
@Setter
@Builder(builderClassName = "Builder", builderMethodName = "create", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "orders", schema = SCHEMA)
@SequenceGenerator(schema = SCHEMA, sequenceName = "seq_orders", name = "seq_orders",
        initialValue = 1, allocationSize = 1)
public class Order extends AbstractAggregateRoot<Order> {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "seq_orders", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "subtotal")
    private BigDecimal subtotal;

    @Column(name = "delivery_fee")
    private BigDecimal deliveryFee;

    @Column(name = "total")
    private BigDecimal total;

    @Embedded
    private Address deliveryAddress;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @CreationTimestamp

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "confirmation_date")
    private OffsetDateTime confirmationDate;

    @Column(name = "cancellation_date")
    private OffsetDateTime cancellationDate;

    @Column(name = "delivery_date")
    private OffsetDateTime deliveryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_client_id", nullable = false)
    private User client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> itens = new ArrayList<>();

    public void calculateTotal() {
        getItens().forEach(OrderItem::calculateTotalPrice);
        this.subtotal = getItens().stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.total = this.subtotal.add(this.deliveryFee);
    }

    public void defineDelivery() {
        setDeliveryFee(getRestaurant().getDeliveryFee());
    }

    public void assignOrderToItems() {
        getItens().forEach(item -> item.setOrder(this));
    }

    public void confirm() {
        setStatus(OrderStatus.CONFIRMADO);
        setConfirmationDate(OffsetDateTime.now());
        registerEvent(new ConfirmedOrderEvent(this));
    }

    public void cancel() {
        setStatus(OrderStatus.CANCELADO);
        setCancellationDate(OffsetDateTime.now());
        registerEvent(new CanceledOrderEvent(this));
    }

    public void deliver() {
        setStatus(OrderStatus.ENTREGUE);
        setDeliveryDate(OffsetDateTime.now());
    }

    private void setStatus(OrderStatus newStatus) {
        if (getStatus().cannotChangeTo(newStatus)) {
            throw new BusinessException(
                    String.format("Status do pedido '%s' não pode ser alterao de %s para %s",
                            getCode(), getStatus(), newStatus.getDescription()));
        }
        this.status = newStatus;
    }

    @PrePersist
    private void generateCode() {
        setCode(UUID.randomUUID().toString());
    }
}
