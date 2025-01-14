package com.revature.integration.repository.user.negative;

import com.revature.integration.repository.user.UserDaoUtil;
import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class DaoFindUserTest extends UserDaoUtil {

    @Parameterized.Parameter(0)
    public String username;

    @Parameterized.Parameters
    public static Collection<Object[]> inputs() {
        return Arrays.asList(new Object[][]{
                {"Bane"},
                {" 1 = 1; SELECT * FROM users;"}
        });
    }
    @Test
    public void findUserByUsernameNegativeTest(){
        Optional<User> response = dao.findUserByUsername(username);
        assertTrue(response.isEmpty());

    }



}
