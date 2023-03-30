package br.santosfyuri.algaworks.algafood.api.representation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "restaurants")
@Getter
@Setter
public class RestaurantResumeResponse extends RepresentationModel<RestaurantResumeResponse> {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Thai Gourmet")
    private String name;

    @Schema(example = "12.00")
    private BigDecimal deliveryFee;
}
