package com.revature.unitTests.service.user.positive;

import com.revature.planetarium.entities.User;
import com.revature.unitTests.service.user.UserServiceUtil;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.*;

public class ServiceUserTest extends UserServiceUtil {


    @Test
    public void createUserPositive() {
        User newUser = new User(0, uniqueUsername, uniquePassword);

        Mockito.when(dao.createUser(newUser))
                .thenReturn(Optional.of(newUser));

        String response = service.createUser(newUser);
        assertEquals(successResponse, response);
    }

    @Test
    public void authenticatePositive() {
        User validUser = new User(1, usedUsername, usedPassword);

        Mockito.when(dao.findUserByUsername(usedUsername)).thenReturn(Optional.of(validUser));

        User response = service.authenticate(validUser);
        assertEquals(usedUsername, response.getUsername());
        assertNull(response.getPassword());
    }
}
