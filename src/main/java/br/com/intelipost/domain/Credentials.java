package br.com.intelipost.domain;

public class Credentials {

    private Integer id;
    private String username;
    private String password;

    public Credentials(String username, Password password) {
        this.username = username;
        this.password = password.getEncodedPassword();
    }

    protected Credentials() {}

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
