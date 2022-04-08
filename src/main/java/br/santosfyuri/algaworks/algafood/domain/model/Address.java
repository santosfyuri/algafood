package br.santosfyuri.algaworks.algafood.domain.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder(builderClassName = "Builder", builderMethodName = "create", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

    @Column(name = "address_cep")
    private String cep;

    @Column(name = "address_street")
    private String street;

    @Column(name = "address_number")
    private String number;

    @Column(name = "address_complement")
    private String complement;

    @Column(name = "address_district")
    private String district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_city_id")
    private City city;
}
