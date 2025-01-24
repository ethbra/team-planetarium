package com.revature.integration.repository.user.positive;

import com.revature.integration.repository.user.UserDaoUtil;
import com.revature.planetarium.entities.User;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class DaoUserTest extends UserDaoUtil {

    /**
     * Unit test to check the nominal example for account creation
     */
    @Test
    public void createUserPositive() {

        Optional<User> response = dao.createUser(positiveUser);

        assertTrue(response.isPresent());

        assertNotEquals(0, response.get().getId());
    }

    /**
     * Unit test to check the nominal example for account search
     */
    @Test
    public void findUserByNamePositive() {
        Optional<User> response = dao.findUserByUsername("Batman");

        assertTrue(response.isPresent());
    }
}
