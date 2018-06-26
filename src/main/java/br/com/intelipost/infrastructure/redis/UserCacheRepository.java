package br.com.intelipost.infrastructure.redis;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCacheRepository extends PagingAndSortingRepository<UserCache, String> {

    UserCache findByEmail(String email);
}
