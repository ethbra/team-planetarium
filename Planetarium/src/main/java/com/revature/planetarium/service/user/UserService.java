package com.revature.planetarium.service.user;

import com.revature.planetarium.entities.User;

public interface UserService {
    
    public String createUser(User newUser);
    public User authenticate(User credentials);

}
