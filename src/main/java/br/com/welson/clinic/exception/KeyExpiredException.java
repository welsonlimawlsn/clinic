package br.com.welson.clinic.exception;

public class KeyExpiredException extends RuntimeException {

    public KeyExpiredException(String message) {
        super(message);
    }
}
