package br.santosfyuri.algaworks.algafood.domain.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import static br.santosfyuri.algaworks.algafood.domain.constants.DatabaseConstants.SCHEMA;

@Getter
@Setter
@Builder(builderClassName = "Builder", builderMethodName = "create")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurants", schema = SCHEMA)
@SequenceGenerator(schema = SCHEMA, sequenceName = "seq_restaurants", name = "seq_restaurants",
        initialValue = 1, allocationSize = 1)
public class Restaurant {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "seq_restaurants", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "delivery_fee")
    private BigDecimal deliveryFee;

    @Column(name = "is_active")
    private boolean active;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    private Kitchen kitchen;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Restaurant that = (Restaurant) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
