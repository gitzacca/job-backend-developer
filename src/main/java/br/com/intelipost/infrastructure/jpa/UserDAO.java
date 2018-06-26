package br.com.intelipost.infrastructure.jpa;

import br.com.intelipost.domain.User;
import br.com.intelipost.domain.UserRepository;
import br.com.intelipost.infrastructure.jpa.entities.UserEntity;
import br.com.intelipost.infrastructure.redis.UserCacheRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO implements UserRepository {

    private UserDataRepository dataRepository;
    private UserCacheRepository userCacheRepository;

    public UserDAO(UserDataRepository dataRepository, UserCacheRepository userCacheRepository) {
        this.dataRepository = dataRepository;
        this.userCacheRepository = userCacheRepository;
    }

    @Override
    public void save(User user) {
        dataRepository.save(new UserEntity(user));
        userCacheRepository.save(user);
    }

}
