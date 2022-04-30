package br.santosfyuri.algaworks.algafood.domain.exception;

public class PaymentMethodNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public PaymentMethodNotFoundException(String message) {
        super(message);
    }

    public PaymentMethodNotFoundException(Long id) {
        this(String.format("Não existe um cadastro de forma de pagamento com código %d", id));
    }
}
