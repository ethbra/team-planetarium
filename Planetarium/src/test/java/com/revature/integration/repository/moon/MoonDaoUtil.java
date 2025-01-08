package com.revature.integration.repository.moon;

import com.revature.Setup;
import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.repository.moon.MoonDaoImp;
import com.revature.planetarium.repository.planet.PlanetDaoImp;
import org.junit.Before;

public class MoonDaoUtil {

    protected static MoonDao dao;

    @Before
    public void setUp() throws Exception {
        Setup.resetTestDatabase();
        dao = new MoonDaoImp();
    }

}
