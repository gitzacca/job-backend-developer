package br.com.intelipost.infrastructure.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCacheRepository extends CrudRepository<UserCache, String> {

    UserCache findByEmail(String email);
}
