package br.santosfyuri.algaworks.algafood.api.representation.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserWithPasswordRequest extends UserRequest{

    private Long id;
    private String name;
    private String email;
}
