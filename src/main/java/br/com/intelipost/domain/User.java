package br.com.intelipost.domain;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@RedisHash("user")
public class User implements Serializable {

    private String id;
    @Indexed private String name;
    @Indexed private String email;
    private Credentials credentials;

    public User(String name, Email email, Credentials credentials) {
        this(name, email);
        this.credentials = credentials;
    }

    public User(String name, Email email) {
        this.id = "user" + email.getValue();
        this.name = name;
        this.email = email.getValue();
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
