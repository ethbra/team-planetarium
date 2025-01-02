package com.revature.planetarium.repository.user;

import java.util.Optional;

import com.revature.planetarium.entities.User;

public interface UserDao {

    Optional<User> createUser(User newUser);
    Optional<User> findUserByUsername(String username);
}
