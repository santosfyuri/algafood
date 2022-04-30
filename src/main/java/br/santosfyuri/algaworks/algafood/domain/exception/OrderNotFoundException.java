package br.santosfyuri.algaworks.algafood.domain.exception;

public class OrderNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public OrderNotFoundException(String code) {
        super(String.format("Não existe um pedido com código %s", code));
    }
}
