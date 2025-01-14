package com.revature.integration.repository.planet.negative;
import com.revature.integration.repository.planet.PlanetDaoUtil;
import com.revature.planetarium.entities.Planet;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class DaoPlanetReadTest extends PlanetDaoUtil{


   @Test
    public void readPlanetsByOwnerNegativeTest(){
        List<Planet> response = dao.readPlanetsByOwner(0);
        assertTrue(response.isEmpty());

    }


}
