package br.santosfyuri.algaworks.algafood.domain.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

import static br.santosfyuri.algaworks.algafood.domain.constants.DatabaseConstants.SCHEMA;

@Getter
@Builder(builderClassName = "Builder", builderMethodName = "create")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "order_items", schema = SCHEMA)
@SequenceGenerator(schema = SCHEMA, sequenceName = "seq_order_items", name = "seq_order_items",
        initialValue = 1, allocationSize = 1)
public class OrderItem {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "seq_order_items", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "note")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
