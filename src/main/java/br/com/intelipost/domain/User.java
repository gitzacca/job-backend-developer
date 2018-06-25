package br.com.intelipost.domain;

import javax.persistence.*;

public class User {

    private Integer id;
    private String name;
    private String email;

    public User(String name, Email email) {
        this.name = name;
        this.email = email.getValue();
    }

    protected User() {}

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}
