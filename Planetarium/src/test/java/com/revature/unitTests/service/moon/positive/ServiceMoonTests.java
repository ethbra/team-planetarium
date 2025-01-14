package com.revature.unitTests.service.moon.positive;

import com.revature.planetarium.entities.Moon;
import com.revature.unitTests.service.moon.MoonServiceUtil;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class ServiceMoonTests extends MoonServiceUtil {
    @Test
    public void createMoonSuccess() {
        Moon validMoonInput = new Moon(0, "My 1st_Moon-", 1);

        Mockito.when(moonDao.readMoon("My 1st_Moon-")).thenReturn(Optional.empty());

        Moon createdMoon = new Moon(3, "My 1st_Moon-", 1);
        Mockito.when(moonDao.createMoon(validMoonInput)).thenReturn(Optional.of(createdMoon));

        Object result = (Object) moonService.createMoon(validMoonInput);

        if (result instanceof Boolean) {
            boolean boolVal = (Boolean) result;
            assertTrue(boolVal);
        } else {
            fail("Should be returning boolean true but instead returning " + result.getClass());
        }
    }

    @Test
    public void selectByPlanetSuccess() {
        int validPlanetId = 2;
        List<Moon> mockMoons = new ArrayList<>();
        mockMoons.add(new Moon(2, "Titan", 2));
        mockMoons.add(new Moon(3, "Nyx_Orion 6-7", 2));

        Mockito.when(moonDao.readMoonsByPlanet(validPlanetId)).thenReturn(mockMoons);

        List<Moon> result = moonService.selectByPlanet(validPlanetId);

        assertNotNull("Returned list should be non-null", result);
        assertFalse("Returned list should be non-empty", result.isEmpty());
        assertEquals("Titan", result.get(0).getMoonName());
        assertEquals("Nyx_Orion 6-7", result.get(1).getMoonName());
    }

    @Test
    public void deleteMoonSuccess() {
        String validMoonName = "Titan";

        Mockito.when(moonDao.deleteMoon(validMoonName)).thenReturn(true);

        Object result = (Object) moonService.deleteMoon(validMoonName);

        if (result instanceof Boolean) {
            boolean boolVal = (Boolean) result;
            assertTrue(boolVal);
        } else {
            fail("Should be returning boolean true but instead returning " + result.getClass());
        }
    }
}

