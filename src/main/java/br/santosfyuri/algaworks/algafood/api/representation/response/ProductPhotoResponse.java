package br.santosfyuri.algaworks.algafood.api.representation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPhotoResponse {

    @Schema(example = "b8bbd21a-4dd3-4954-835c-3493af2ba6a0_Prime-Rib.jpg")
    private String fileName;

    @Schema(example = "Prime Rib")
    private String description;

    @Schema(example = "image/jpeg")
    private String contentType;

    @Schema(example = "202912")
    private Long size;
}
