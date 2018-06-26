package br.com.intelipost.domain;

public interface UserRepository {

    void save(User user);

    User findBy(Email email);

}
