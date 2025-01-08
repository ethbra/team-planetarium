package com.revature.integration.repository.user.positive;

import com.revature.integration.repository.user.UserDaoUtil;
import com.revature.planetarium.entities.User;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class DaoUserTest extends UserDaoUtil {

//TODO: each of these should have sad path tests
//    Nomenclature should go by root name, then the broken requirement
//    e.g., "createUserNameAlreadyExists"

    private final User positiveUser = new User(0, "Super_man-2001", "Krypton-was_2000");


    /**
     * Unit test to check the nominal example for account creation
     */
    @Test
    public void createUser() {

        Optional<User> response = dao.createUser(positiveUser);
        assertTrue(response.isPresent());
        System.out.println("response.toString() = " + response);
        assertNotEquals(0, response.get().getId());
    }

    /**
     * Unit test to check the nominal example for account search
     */
    @Test
    public void findUserByName() {
        Optional<User> response = dao.findUserByUsername("Batman");
        System.out.println("response.toString() = " + response.toString());
        assertTrue(response.isPresent());
    }
}
