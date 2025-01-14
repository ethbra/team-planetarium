package com.revature.unitTests.service.user.negative;

import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.UserFail;
import com.revature.unitTests.service.user.UserServiceUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class ServiceAuthenticateTest extends UserServiceUtil {

    @Parameter(0)
    public String username;

    @Parameter(1)
    public String password;

    @Parameter(2)
    public int id;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                        {"Batman", "WeKillTheBatman", 0},
                        {"Joker", "ImSuperEvil123", 0},
                        {"WeKillTheBatman", "Iamthenight1939", 0}
                }
        );
    }

    @Test
    public void authenticateNegative() {
        User user = new User(id, username, password);

        Mockito.when(dao.findUserByUsername(Mockito.anyString()))
                .thenReturn(Optional.empty());
        Mockito.when(dao.findUserByUsername("Batman"))
                .thenReturn(Optional.of(user));
        UserFail fail = assertThrows(UserFail.class, () -> service.authenticate(user));

        assertEquals(authFailureResponse, fail.getMessage());

    }
}
