package com.revature.unitTests.service.user;

import com.revature.Setup;
import com.revature.planetarium.repository.user.UserDaoImp;
import com.revature.planetarium.service.user.UserService;
import com.revature.planetarium.service.user.UserServiceImp;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;

public class UserServiceUtil {
    @Mock
    protected UserDaoImp dao;

    protected UserService service;
    protected final String usedUsername = "Batman";
    protected final String usedPassword = "Iamthenight1939";

    protected final String uniqueUsername = "Super_man-2001";
    protected final String uniquePassword = "Krypton-was_2000";

    protected final String successResponse = "User created successfully";
    protected final String authFailureResponse = "Invalid credentials";
    @Before
    public void setup() {
        Setup.resetTestDatabase();

        dao = Mockito.mock(UserDaoImp.class);

        service = new UserServiceImp(dao);
    }

}
