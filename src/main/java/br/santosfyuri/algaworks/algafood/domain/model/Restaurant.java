package br.santosfyuri.algaworks.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static br.santosfyuri.algaworks.algafood.domain.constants.DatabaseConstants.SCHEMA;

@Getter
@Setter
@Builder(builderClassName = "Builder", builderMethodName = "create")
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
    @Column(name = "id")
    @GeneratedValue(generator = "seq_restaurants", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "delivery_fee")
    private BigDecimal deliveryFee;

    @Column(name = "active")
    private boolean active;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Embedded
    private Address address;

    @JsonIgnoreProperties("hibernateLazyInitializer")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kitchen_id")
    private Kitchen kitchen;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "restaurants_payments_methods", joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
    private List<PaymentMethod> paymentMethods = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Product> products = new ArrayList<>();
}
