package br.com.intelipost.domain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Password {

    private String encodedPassword;

    public Password(String password) {
        this.encodedPassword = new BCryptPasswordEncoder().encode(password);
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }
}
