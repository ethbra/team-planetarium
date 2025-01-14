package com.revature.integration.repository.planet.negative;


import com.revature.integration.repository.planet.PlanetDaoUtil;
import com.revature.planetarium.exceptions.PlanetFail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

public class DaoPlanetDeleteTest extends PlanetDaoUtil {

    @Test
    public void deletePlanetNegative() {
        PlanetFail e = Assert.assertThrows(PlanetFail.class, () ->
                dao.deletePlanet("Nonexistant-Planet")
        );

        assertEquals("Invalid planet name", e.getMessage());
    }
}
