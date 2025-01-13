package com.revature.unitTests.service.planet;

import com.revature.Setup;
import com.revature.planetarium.repository.planet.PlanetDao;
import com.revature.planetarium.repository.planet.PlanetDaoImp;
import com.revature.planetarium.service.planet.PlanetService;
import com.revature.planetarium.service.planet.PlanetServiceImp;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;

public class ServicePlanetUtil {

    protected PlanetService service;
    @Mock
    protected PlanetDao dao;

    @Before
    public void setup() {
        Setup.resetTestDatabase();

        dao = Mockito.mock(PlanetDaoImp.class);

        service = new PlanetServiceImp(dao);
    }

}
