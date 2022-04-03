package br.santosfyuri.algaworks.algafood.domain.model;

import br.santosfyuri.algaworks.algafood.core.validation.Groups;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

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

    @NotBlank
    @Column(name = "name")
    private String name;

    @Valid
    @ConvertGroup(to = Groups.StateId.class)
    @NotNull
    @ManyToOne
    private State state;
}
