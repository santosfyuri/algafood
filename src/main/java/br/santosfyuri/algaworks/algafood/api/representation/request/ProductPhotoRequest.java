package br.santosfyuri.algaworks.algafood.api.representation.request;

import br.santosfyuri.algaworks.algafood.domain.validation.FileContentType;
import br.santosfyuri.algaworks.algafood.domain.validation.FileSize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductPhotoRequest {

    @Schema(example = "Product photo file (maximum 500KB, JPG and PNG only)", required = true)
    @NotNull
    @FileSize(max = "500KB")
    @FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    private MultipartFile archive;

    @Schema(example = "Product photo description", required = true)
    @NotBlank
    private String description;
}
