package br.com.intelipost.infrastructure.dao;

public class UserNotFoundException extends RuntimeException {

    UserNotFoundException(String message) {
        super(message);
    }

}
