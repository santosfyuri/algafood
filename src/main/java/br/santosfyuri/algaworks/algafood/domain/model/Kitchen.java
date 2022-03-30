package br.santosfyuri.algaworks.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static br.santosfyuri.algaworks.algafood.domain.constants.DatabaseConstants.SCHEMA;

@Getter
@Builder(builderClassName = "Builder", builderMethodName = "create")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "kitchens", schema = SCHEMA)
@SequenceGenerator(schema = SCHEMA, sequenceName = "seq_kitchens", name = "seq_kitchens",
        initialValue = 1, allocationSize = 1)
public class Kitchen {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "seq_kitchens", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", length = 30)
    private String name;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kitchen")
    private List<Restaurant> restaurants = new ArrayList<>();
}



