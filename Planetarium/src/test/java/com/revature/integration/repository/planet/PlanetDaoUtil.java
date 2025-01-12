package com.revature.integration.repository.planet;

import com.revature.Setup;
import com.revature.planetarium.repository.planet.PlanetDao;
import com.revature.planetarium.repository.planet.PlanetDaoImp;
import org.junit.Before;

public class PlanetDaoUtil {

    protected static PlanetDao dao;

    @Before
    public void setUp() throws Exception {
        Setup.resetTestDatabase();
        dao = new PlanetDaoImp();
    }

}
