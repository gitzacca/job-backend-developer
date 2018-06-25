package br.com.intelipost.infrastructure.jpa;

import br.com.intelipost.domain.Password;

import javax.persistence.*;

@Entity
public class CredentialsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public CredentialsEntity(String username, Password password) {
        this.username = username;
        this.password = password.getEncodedPassword();
    }

    protected CredentialsEntity() {}

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
