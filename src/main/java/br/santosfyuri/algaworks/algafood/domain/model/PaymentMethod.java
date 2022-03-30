package br.santosfyuri.algaworks.algafood.domain.model;

import lombok.*;

import javax.persistence.*;

import static br.santosfyuri.algaworks.algafood.domain.constants.DatabaseConstants.SCHEMA;

@Getter
@Builder(builderClassName = "Builder", builderMethodName = "create")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "payment_methods", schema = SCHEMA)
@SequenceGenerator(schema = SCHEMA, sequenceName = "seq_payment_methods", name = "seq_payment_methods",
        initialValue = 1, allocationSize = 1)
public class PaymentMethod {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "seq_payment_methods", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "description")
    private String description;
}
