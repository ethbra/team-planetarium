package com.revature.unitTests.service.moon.negative;

import com.revature.planetarium.entities.Moon;
import com.revature.unitTests.service.moon.MoonServiceUtil;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ServiceMoonRetrievalTest extends MoonServiceUtil {

    @Test
    public void selectByPlanetFailure() {
        int planetIdWithNoMoons = 10000; 
        Mockito.when(moonDao.readMoonsByPlanet(planetIdWithNoMoons)).thenReturn(new ArrayList<>());
        List<Moon> result = moonService.selectByPlanet(planetIdWithNoMoons);

        assertNotNull("Result should not be null", result);
        assertTrue("Expected an empty list for no Moons found", result.isEmpty());
    }
}
