package com.revature.integration.repository.moon.negative;

import com.revature.integration.repository.moon.MoonDaoUtil;
import com.revature.planetarium.entities.Moon;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class DaoMoonReadTest extends MoonDaoUtil {

    @Test
    public void readMoonsByPlanetNegative() {
        List<Moon> response = dao.readMoonsByPlanet(0);
        assertTrue(response.isEmpty());
    }

}
