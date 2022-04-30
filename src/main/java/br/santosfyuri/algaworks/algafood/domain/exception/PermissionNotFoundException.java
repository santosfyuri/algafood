package br.santosfyuri.algaworks.algafood.domain.exception;

public class PermissionNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public PermissionNotFoundException(String message) {
        super(message);
    }

    public PermissionNotFoundException(Long id) {
        this(String.format("Não existe um cadastro de permissão com código %d.", id));
    }
}
