package br.santosfyuri.algaworks.algafood.api.representation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserWithPasswordRequest extends UserRequest {

    private Long id;
    private String name;
    private String email;

    @Schema(example = "123", required = true)
    @NotBlank
    private String senha;
}
