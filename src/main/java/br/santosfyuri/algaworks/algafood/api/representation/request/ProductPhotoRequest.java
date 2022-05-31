package br.santosfyuri.algaworks.algafood.api.representation.request;

import br.santosfyuri.algaworks.algafood.domain.validation.FileContentType;
import br.santosfyuri.algaworks.algafood.domain.validation.FileSize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductPhotoRequest {

    @NotNull
    @FileSize(max = "500KB")
    @FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    private MultipartFile archive;

    @NotBlank
    private String description;
}
