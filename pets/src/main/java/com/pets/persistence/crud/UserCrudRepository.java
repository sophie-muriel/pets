package com.pets.persistence.crud;

import com.pets.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserCrudRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByLogin(String login);
}