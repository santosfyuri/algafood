package br.santosfyuri.algaworks.algafood.domain.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
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
@Table(name = "users", schema = SCHEMA)
@SequenceGenerator(schema = SCHEMA, sequenceName = "seq_users", name = "seq_users",
        initialValue = 1, allocationSize = 1)
public class User {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @ManyToMany
    @JoinTable(name = "users_groups", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups = new HashSet<>();

    public boolean samePassword(String password) {
        return getPassword().equals(password);
    }

    public boolean differentPassword(String password) {
        return !samePassword(password);
    }

    public boolean removeGroup(Group group) {
        return getGroups().remove(group);
    }

    public boolean addGroup(Group group) {
        return getGroups().add(group);
    }
}
