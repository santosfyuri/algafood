package br.santosfyuri.algaworks.algafood.api.representation.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPhotoResponse {

    private String fileName;
    private String description;
    private String contentType;
    private Long size;
}
