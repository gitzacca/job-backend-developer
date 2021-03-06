package br.com.intelipost.infrastructure.dao;

import br.com.intelipost.domain.Email;
import br.com.intelipost.domain.User;
import br.com.intelipost.domain.UserRepository;
import br.com.intelipost.domain.exceptions.UserNotFoundException;
import br.com.intelipost.infrastructure.jpa.UserDataRepository;
import br.com.intelipost.infrastructure.jpa.entities.UserEntity;
import br.com.intelipost.infrastructure.redis.UserCache;
import br.com.intelipost.infrastructure.redis.UserCacheRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        userCacheRepository.save(new UserCache(user));
    }

    @Override
    public User findBy(Email email) {
        UserCache userCache = userCacheRepository.findByEmail(email.getValue());

        if (userCache != null) {
            return  userCache.toUser();
        }

        UserEntity userEntity = dataRepository.findByEmail(email.getValue());

        if (userEntity != null) {
            userEntity.toUser();
        }

        throw new UserNotFoundException("Usuário com email: " + email.getValue() + " não encontrado!");
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        Page<UserCache> listFromCache = userCacheRepository.findAll(pageable);
        return listFromCache.map(UserCache::toUser);
    }

}
