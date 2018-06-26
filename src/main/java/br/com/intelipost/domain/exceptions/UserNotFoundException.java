package br.com.intelipost.domain.exceptions;

public class UserNotFoundException extends RuntimeException {

    UserNotFoundException(String message) {
        super(message);
    }

}
