package net.wismas.spring.skeleton.api.login.jpa;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by alexis.delaporte on 22/05/2017.
 */
public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
