package com.revature.integration.repository.planet.negative;

import com.revature.integration.repository.planet.PlanetDaoUtil;
import com.revature.planetarium.entities.Planet;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class DaoPlanetReadTest extends PlanetDaoUtil {

    @Parameterized.Parameter()
    public int ownerId;

    @Parameterized.Parameters
    public static Collection<Object[]> inputs() {
        return Arrays.asList(new Object[][]{
                {0},
                {-1},
                {Integer.MAX_VALUE},
                {Integer.MIN_VALUE}
        });
    }


    @Test
    public void readPlanetsByOwnerNegativeTest() {
        List<Planet> response = dao.readPlanetsByOwner(ownerId);
        assertTrue(response.isEmpty());

    }


}
