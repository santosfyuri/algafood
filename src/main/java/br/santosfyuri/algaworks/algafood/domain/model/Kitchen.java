package br.santosfyuri.algaworks.algafood.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

import static br.santosfyuri.algaworks.algafood.domain.constants.DatabaseConstants.SCHEMA;

@Getter
@Builder(builderClassName = "Builder", builderMethodName = "create")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "kitchens", schema = SCHEMA)
@SequenceGenerator(schema = SCHEMA, sequenceName = "seq_kitchens", name = "seq_kitchens",
        initialValue = 1, allocationSize = 1)
public class Kitchen {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "seq_kitchens", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", length = 30)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Kitchen kitchen = (Kitchen) o;
        return id != null && Objects.equals(id, kitchen.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}



