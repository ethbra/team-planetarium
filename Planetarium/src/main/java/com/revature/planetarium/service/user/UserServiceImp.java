package com.revature.planetarium.service.user;


import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.UserFail;
import com.revature.planetarium.repository.user.UserDao;

import java.util.Optional;

public class UserServiceImp implements UserService {

    private UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String createUser(User newUser) {
        if (newUser.getId() != 0) throw new UserFail("Invalid ID");

        Optional<User> daoUser = userDao.findUserByUsername(newUser.getUsername());
        if (daoUser.isPresent()) {
            System.out.println("User already exists in service layer");
            throw new UserFail("Invalid username");
        }



        Optional<User> createdUser = userDao.createUser(newUser);

        if (createdUser.isPresent()) {
            System.out.println("User created successfully");
            return "User created successfully";
        }

        System.out.println("I don't know how it gets down here");
        throw new UserFail("Invalid username");

    }

    @Override
    public User authenticate(User credentials) {

        Optional<User> foundUser = userDao.findUserByUsername(credentials.getUsername());
        foundUser.ifPresent(user -> System.out.println(user.getUsername() + ", " + user.getPassword()));

        if (foundUser.isPresent() && foundUser.get().getPassword().equals(credentials.getPassword())) {
            User response = new User();
            response.setUsername(credentials.getUsername());
            response.setId(foundUser.get().getId());

            return response;
        }

        throw new UserFail("Invalid credentials");
    }
}
