package net.wismas.spring.skeleton.api.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by conta on 12/07/2017.
 */
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    List<UserEntity> findByLogin(String login);
}
