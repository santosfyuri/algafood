package br.santosfyuri.algaworks.algafood.domain.model;

import br.santosfyuri.algaworks.algafood.core.validation.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotNull(groups = Groups.KitchenId.class)
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "seq_kitchens", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Column(name = "name", length = 30)
    private String name;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kitchen")
    private List<Restaurant> restaurants = new ArrayList<>();
}



