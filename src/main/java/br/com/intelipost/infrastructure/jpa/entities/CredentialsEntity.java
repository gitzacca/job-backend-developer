package br.com.intelipost.infrastructure.jpa.entities;

import br.com.intelipost.domain.Credentials;

import javax.persistence.*;

@Entity
@Table(name = "credentials")
public class CredentialsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public CredentialsEntity(Credentials credentials) {
        this.username = credentials.getUsername();
        this.password = credentials.getPassword();
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
