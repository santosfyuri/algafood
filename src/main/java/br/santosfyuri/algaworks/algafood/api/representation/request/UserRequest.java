package br.santosfyuri.algaworks.algafood.api.representation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserRequest {

    @Schema(example = "John", required = true)
    @NotBlank
    private String name;

    @Schema(example = "john@email.com", required = true)
    @NotBlank
    @Email
    private String email;
}
