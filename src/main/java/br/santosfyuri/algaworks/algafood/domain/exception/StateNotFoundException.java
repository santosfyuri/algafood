package br.santosfyuri.algaworks.algafood.domain.exception;

public class StateNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public StateNotFoundException(String message) {
        super(message);
    }

    public StateNotFoundException(Long id) {
        this(String.format("Não existe um cadastro de estado com código %d.", id));
    }
}
