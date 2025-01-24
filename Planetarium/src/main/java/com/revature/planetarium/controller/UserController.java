package com.revature.planetarium.controller;

import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.AuthenticationFailed;
import com.revature.planetarium.exceptions.UserFail;
import com.revature.planetarium.service.user.UserService;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UserController {

    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void createUser(Context ctx) {

        Map<String, String> response = new HashMap<>();

        try {

            User user = ctx.bodyAsClass(User.class);
            String result = userService.createUser(user);

            response.put("message", result);
            ctx.status(201).json(response);

        } catch (UserFail e) {
            System.err.println("User creation failed, " + e.getMessage());
            response.put("message", e.getMessage());

            ctx.status(400).json(response);
        }
    }

    public void login(Context ctx) {

        User credentials = ctx.bodyAsClass(User.class);

        try {
            credentials = userService.authenticate(credentials);

            ctx.sessionAttribute("user", credentials);

            ctx.status(200).json(Map.of("username", credentials.getUsername(),
                    "id", credentials.getId()));

        } catch (UserFail e) {
            ctx.status(401).json(Map.of("message", "invalid credentials"));
        }
    }

    private class UserDto {
        private String username;
        private int id;

        private UserDto(String un, int id) {
            this.id = id;
            this.username = un;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }


    public void logout(Context ctx) {
        ctx.req().getSession().invalidate();
        ctx.status(200);
    }

    public void authenticateUser(Context ctx) {
        if (ctx.req().getSession(false) == null) {
            throw new AuthenticationFailed("Please log in first");
        }
    }
}
