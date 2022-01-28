package br.santosfyuri.algaworks.algafood.domain.model;

import lombok.*;

import javax.persistence.*;

import static br.santosfyuri.algaworks.algafood.domain.constants.DatabaseConstants.SCHEMA;

@Getter
@Setter
@Builder(builderClassName = "Builder", builderMethodName = "create")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "states", schema = SCHEMA)
@SequenceGenerator(schema = SCHEMA, sequenceName = "seq_states", name = "seq_states",
        initialValue = 1, allocationSize = 1)
public class State {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "seq_states", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;
}
