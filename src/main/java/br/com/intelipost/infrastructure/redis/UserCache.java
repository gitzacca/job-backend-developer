package br.com.intelipost.infrastructure.redis;

import br.com.intelipost.domain.Email;
import br.com.intelipost.domain.User;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;
import java.io.Serializable;

@RedisHash("users")
public class UserCache implements Serializable {

    @Id
    private String id;

    @Indexed
    private String name;

    @Indexed
    private String email;

    public UserCache(User user) {
        this.id = "users:" + user.getEmail();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    protected UserCache() {}

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public User toUser() {
        return new User(name, new Email(email));
    }
}
