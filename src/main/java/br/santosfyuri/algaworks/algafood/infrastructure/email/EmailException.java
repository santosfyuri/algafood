package br.santosfyuri.algaworks.algafood.infrastructure.email;

public class EmailException extends RuntimeException {

    private static final Long serialVersionUID = 1L;

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailException(String message) {
        super(message);
    }
}
