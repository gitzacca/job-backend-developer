package br.com.intelipost.infrastructure.jpa;

import br.com.intelipost.domain.Email;

import javax.persistence.*;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @ManyToOne
    private CredentialsEntity credentials;

    public UserEntity(String name, Email email) {
        this.name = name;
        this.email = email.getValue();
    }

    protected UserEntity() {}

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public CredentialsEntity getCredentials() { return credentials; }
}
