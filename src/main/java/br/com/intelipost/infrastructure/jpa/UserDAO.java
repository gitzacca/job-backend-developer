package br.com.intelipost.infrastructure.jpa;

import br.com.intelipost.domain.User;
import br.com.intelipost.domain.UserRepository;
import br.com.intelipost.infrastructure.jpa.entities.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO implements UserRepository {

    private UserDataRepository dataRepository;

    public UserDAO(UserDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public void save(User user) {
        dataRepository.save(new UserEntity(user));
    }

}
