package br.santosfyuri.algaworks.algafood.domain.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static br.santosfyuri.algaworks.algafood.domain.constants.DatabaseConstants.SCHEMA;

@Getter
@Setter
@Builder(builderClassName = "Builder", builderMethodName = "create", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "restaurants", schema = SCHEMA)
@SequenceGenerator(schema = SCHEMA, sequenceName = "seq_restaurants", name = "seq_restaurants",
        initialValue = 1, allocationSize = 1)
public class Restaurant {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(generator = "seq_restaurants", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "delivery_fee")
    private BigDecimal deliveryFee;

    @Column(name = "active")
    private boolean active = Boolean.TRUE;

    @Column(name = "open")
    private boolean open = Boolean.FALSE;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @Embedded
    private Address address;

    @Setter
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "kitchen_id", nullable = false)
    private Kitchen kitchen;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "restaurants_payment_methods", joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
    private Set<PaymentMethod> paymentMethods = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Product> products = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "responsible_restaurant_users",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> responsibleUsers = new HashSet<>();

    public void active() {
        this.setActive(true);
    }

    public void inactive() {
        this.setActive(false);
    }

    public void open() {
        this.setOpen(true);
    }

    public void close() {
        this.setOpen(false);
    }

    public boolean removePaymentMethod(PaymentMethod paymentMethod) {
        return getPaymentMethods().remove(paymentMethod);
    }

    public boolean addPaymentMethod(PaymentMethod paymentMethod) {
        return getPaymentMethods().add(paymentMethod);
    }

    public boolean removeResponsibleUser(User user) {
        return getResponsibleUsers().remove(user);
    }

    public boolean addResponsibleUser(User user) {
        return getResponsibleUsers().add(user);
    }

    public boolean acceptsPaymentMethod(PaymentMethod paymentMethod) {
        return getPaymentMethods().contains(paymentMethod);
    }

    public boolean notAcceptsPaymentMethod(PaymentMethod paymentMethod) {
        return !acceptsPaymentMethod(paymentMethod);
    }
}
