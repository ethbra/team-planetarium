package com.revature.integration.repository.user;

import com.revature.Setup;
import com.revature.planetarium.repository.user.UserDao;
import com.revature.planetarium.repository.user.UserDaoImp;
import org.junit.Before;

public class UserDaoUtil {

    protected UserDao dao;


    @Before
    public void setUp() throws Exception {
        Setup.resetTestDatabase();
        dao = new UserDaoImp();
    }

}
