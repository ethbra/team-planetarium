package com.revature.integration.repository.user;

import com.revature.Setup;
import com.revature.planetarium.entities.User;
import com.revature.planetarium.repository.user.UserDao;
import com.revature.planetarium.repository.user.UserDaoImp;
import org.junit.Before;

public class UserDaoUtil {

    protected UserDao dao;
    protected final User positiveUser = new User(0, "Super_man-2001", "Krypton-was_2000");


    @Before
    public void setUp() throws Exception {
        Setup.resetTestDatabase();
        dao = new UserDaoImp();
    }

}
