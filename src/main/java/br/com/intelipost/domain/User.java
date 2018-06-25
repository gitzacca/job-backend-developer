package br.com.intelipost.domain;


public class User {

    private Integer id;
    private String name;
    private String email;
    private Credentials credentials;

    public User(String name, Email email, Credentials credentials) {
        this.name = name;
        this.email = email.getValue();
        this.credentials = credentials;
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

    public Credentials getCredentials() { return credentials; }
}
