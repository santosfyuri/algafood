package br.santosfyuri.algaworks.algafood.domain.exception;

public class RestaurantNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public RestaurantNotFoundException(String message) {
        super(message);
    }

    public RestaurantNotFoundException(Long id) {
        this(String.format("Não existe um cadastro de restaurante com código %d.", id));
    }
}
