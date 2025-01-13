package com.revature.integration.repository.planet.negative;


import com.revature.integration.repository.planet.PlanetDaoUtil;
import com.revature.planetarium.exceptions.PlanetFail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class DaoDeletePlanetNegative extends PlanetDaoUtil {


    @Parameterized.Parameter(0)
    public String planetName;

    @Parameterized.Parameters
    public static Collection<Object[]> inputs() {
        return Arrays.asList(new Object[][]{
                {"Saturn"},
                {"Pr()xim@ Centaur! B"},
                {"ThisNameIsOverThirtyCharactersLong"},
        });
    }
    @Test
    public void deletePlanetNegative()
    {
        PlanetFail e = Assert.assertThrows(
                PlanetFail.class,
                () -> dao.deletePlanet(planetName)
        );

        assertEquals("Invalid planet name", e.getMessage());
    }

}
