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
@Table(name = "cities", schema = SCHEMA)
@SequenceGenerator(schema = SCHEMA, sequenceName = "seq_cities", name = "seq_cities",
        initialValue = 1, allocationSize = 1)
public class City {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "seq_cities", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    private State state;
}
