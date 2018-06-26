package br.com.intelipost.infrastructure.redis;

import br.com.intelipost.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCacheRepository extends CrudRepository<User, String> { }
