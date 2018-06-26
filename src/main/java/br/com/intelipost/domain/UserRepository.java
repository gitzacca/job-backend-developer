package br.com.intelipost.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepository {

    void save(User user);

    User findBy(Email email);

    Page<User> findAll(Pageable pageable);

}
