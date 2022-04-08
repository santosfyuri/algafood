package br.santosfyuri.algaworks.algafood.domain.model;

import br.santosfyuri.algaworks.algafood.core.validation.Groups;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static br.santosfyuri.algaworks.algafood.domain.constants.DatabaseConstants.SCHEMA;

@Getter
@Setter
@Builder(builderClassName = "Builder", builderMethodName = "create", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "states", schema = SCHEMA)
@SequenceGenerator(schema = SCHEMA, sequenceName = "seq_states", name = "seq_states",
        initialValue = 1, allocationSize = 1)
public class State {

    @NotNull(groups = Groups.StateId.class)
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "seq_states", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;
}
