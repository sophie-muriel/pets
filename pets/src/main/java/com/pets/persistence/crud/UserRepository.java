package com.pets.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import com.pets.persistence.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
