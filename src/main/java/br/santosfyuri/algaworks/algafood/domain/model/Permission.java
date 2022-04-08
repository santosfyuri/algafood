package br.santosfyuri.algaworks.algafood.domain.model;

import lombok.*;

import javax.persistence.*;

import static br.santosfyuri.algaworks.algafood.domain.constants.DatabaseConstants.SCHEMA;

@Getter
@Builder(builderClassName = "Builder", builderMethodName = "create", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "permissions", schema = SCHEMA)
@SequenceGenerator(schema = SCHEMA, sequenceName = "seq_permissions", name = "seq_permissions",
        initialValue = 1, allocationSize = 1)
public class Permission {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "seq_permissions", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
