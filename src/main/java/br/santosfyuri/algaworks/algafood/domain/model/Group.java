package br.santosfyuri.algaworks.algafood.domain.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static br.santosfyuri.algaworks.algafood.domain.constants.DatabaseConstants.SCHEMA;

@Getter
@Setter
@Builder(builderClassName = "Builder", builderMethodName = "create", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "groups", schema = SCHEMA)
@SequenceGenerator(schema = SCHEMA, sequenceName = "seq_groups", name = "seq_groups",
        initialValue = 1, allocationSize = 1)
public class Group {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "seq_groups", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "groups_permissions", joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions = new HashSet<>();

    public boolean removePermission(Permission permission) {
        return getPermissions().remove(permission);
    }

    public boolean addPermission(Permission permission) {
        return getPermissions().add(permission);
    }
}
