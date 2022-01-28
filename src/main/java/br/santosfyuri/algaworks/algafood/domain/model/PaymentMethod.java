package br.santosfyuri.algaworks.algafood.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static br.santosfyuri.algaworks.algafood.domain.constants.DatabaseConstants.SCHEMA;

@Getter
@Builder(builderClassName = "Builder", builderMethodName = "create")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment_methods", schema = SCHEMA)
@SequenceGenerator(schema = SCHEMA, sequenceName = "seq_payment_methods", name = "seq_payment_methods",
        initialValue = 1, allocationSize = 1)
public class PaymentMethod {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "seq_payment_methods", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "description")
    private String description;
}
