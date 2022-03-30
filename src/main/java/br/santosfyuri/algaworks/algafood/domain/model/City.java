package br.santosfyuri.algaworks.algafood.domain.model;

import lombok.*;

import javax.persistence.*;

import static br.santosfyuri.algaworks.algafood.domain.constants.DatabaseConstants.SCHEMA;

@Getter
@Setter
@Builder(builderClassName = "Builder", builderMethodName = "create")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "cities", schema = SCHEMA)
@SequenceGenerator(schema = SCHEMA, sequenceName = "seq_cities", name = "seq_cities",
        initialValue = 1, allocationSize = 1)
public class City {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "seq_cities", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    private State state;
}
