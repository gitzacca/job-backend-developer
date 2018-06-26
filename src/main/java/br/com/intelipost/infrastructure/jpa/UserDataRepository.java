package br.com.intelipost.infrastructure.jpa;

import br.com.intelipost.infrastructure.jpa.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserDataRepository extends CrudRepository<UserEntity, Integer> {

    UserEntity findByEmail(String email);

}
