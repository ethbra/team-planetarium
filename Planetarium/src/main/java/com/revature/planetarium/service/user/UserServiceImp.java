package com.revature.planetarium.service.user;


import java.util.Optional;

import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.UserFail;
import com.revature.planetarium.repository.user.UserDao;

public class UserServiceImp implements UserService {
    
    private UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String createUser(User newUser) {
        Optional<User> createdUser = userDao.createUser(newUser);
        if (createdUser.isPresent()) {
            return "Created user with username " + createdUser.get().getUsername() + " and password " + createdUser.get().getPassword();
        } else {
            throw new UserFail("Failed to create user, please try again");
        }
    }

    @Override
    public User authenticate(User credentials) {
        Optional<User> foundUser = userDao.findUserByUsername(credentials.getUsername());
        if (foundUser.isPresent()) {
            if (foundUser.get().getPassword().equals(credentials.getPassword())) {
                return foundUser.get();
            }
        }
        throw new UserFail("Invalid credentials");
    }

}
