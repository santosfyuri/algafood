package br.santosfyuri.algaworks.algafood.infrastructure.storage;

public class StorageException extends RuntimeException {

    private static final Long serialVersionUID = 1L;

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public StorageException(String message) {
        super(message);
    }
}
