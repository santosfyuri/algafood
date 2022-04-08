package br.santosfyuri.algaworks.algafood.domain.model;

import br.santosfyuri.algaworks.algafood.domain.enums.OrderStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static br.santosfyuri.algaworks.algafood.domain.constants.DatabaseConstants.SCHEMA;

@Getter
@Builder(builderClassName = "Builder", builderMethodName = "create", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "orders", schema = SCHEMA)
@SequenceGenerator(schema = SCHEMA, sequenceName = "seq_orders", name = "seq_orders",
        initialValue = 1, allocationSize = 1)
public class Order {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "seq_orders", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "subtotal")
    private BigDecimal subtotal;

    @Column(name = "shipping_fee")
    private BigDecimal shippingFee;

    @Column(name = "amount")
    private BigDecimal amount;

    @Embedded
    private Address deliveryAddress;

    @Column(name = "status")
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

    @ManyToOne
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_client_id", nullable = false)
    private User client;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> itens = new ArrayList<>();
}
