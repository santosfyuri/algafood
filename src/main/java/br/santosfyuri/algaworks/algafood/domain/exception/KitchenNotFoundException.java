package br.santosfyuri.algaworks.algafood.domain.exception;

public class KitchenNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public KitchenNotFoundException(String message) {
        super(message);
    }

    public KitchenNotFoundException(Long id) {
        this(String.format("Não existe um cadastro de cozinha com código %d.", id));
    }
}
