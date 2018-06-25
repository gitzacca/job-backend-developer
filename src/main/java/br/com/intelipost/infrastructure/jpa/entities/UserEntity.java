package br.com.intelipost.infrastructure.jpa.entities;

import br.com.intelipost.domain.Email;
import br.com.intelipost.domain.User;

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

    public UserEntity(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.credentials = new CredentialsEntity(user.getCredentials());
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
