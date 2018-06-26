package br.com.intelipost.domain;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("User")
public class User implements Serializable {

    private String id;
    private String name;
    private String email;
    private Credentials credentials;

    public User(String name, Email email, Credentials credentials) {
        this.id = "user" + email.getValue();
        this.name = name;
        this.email = email.getValue();
        this.credentials = credentials;
    }

    protected User() {}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Credentials getCredentials() { return credentials; }
}
