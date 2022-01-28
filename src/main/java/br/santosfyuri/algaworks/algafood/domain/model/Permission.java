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
@Table(name = "permissions", schema = SCHEMA)
@SequenceGenerator(schema = SCHEMA, sequenceName = "seq_permissions", name = "seq_permissions",
        initialValue = 1, allocationSize = 1)
public class Permission {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "seq_permissions", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
