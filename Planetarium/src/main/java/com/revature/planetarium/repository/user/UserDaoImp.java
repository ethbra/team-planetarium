package com.revature.planetarium.repository.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.stream.Stream;

import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.UserFail;
import com.revature.planetarium.utility.DatabaseConnector;

public class UserDaoImp implements UserDao {

    @Override
    public Optional<User> createUser(User newUser) {
        String u = newUser.getUsername();
        if (newUser.getId() != 0)
            throw new UserFail("Invalid ID");

        boolean goodUsername = !u.isEmpty() && u.length() <= 30 && Character.isLetter(u.charAt(0)) && u.chars().allMatch(c -> Character.toString((char)c).matches("[A-Za-z0-9_-]"));

        if (!goodUsername) {
            System.err.println("I make a terrible regex");
            throw new UserFail("Invalid username");
        }

        if (newUser.getId() != 0) throw new UserFail("Invalid ID");



        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, newUser.getUsername());
            stmt.setString(2, newUser.getPassword());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    newUser.setId(rs.getInt(1));
                    return Optional.of(newUser);
                } else {
                    System.err.println("Invalid password in dao layer");
                    throw new UserFail("Invalid password");
                }
            }
        } catch (SQLException e) {
            String errMsg = e.getMessage();
            System.out.println("errMsg = " + errMsg);
            if (errMsg.contains("username")) {
                throw new UserFail("Invalid username");
            } else
                throw new UserFail("Invalid password");
        }
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?")) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    return Optional.of(user);
                }
            }
            return Optional.empty();
        } catch (SQLException e) {
            System.out.println(e);
            throw new UserFail(e.getMessage());
        }
    }


}
