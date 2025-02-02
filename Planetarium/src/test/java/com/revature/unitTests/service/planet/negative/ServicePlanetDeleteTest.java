package com.revature.unitTests.service.planet.negative;

import com.revature.planetarium.entities.Planet;
import com.revature.unitTests.service.planet.ServicePlanetUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;


@RunWith(Parameterized.class)
public class ServicePlanetDeleteTest extends ServicePlanetUtil {
    @Parameter(0)
    public Object invalidInput;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            //planetName                          
            {new Planet()},
            {"Saturn"}                              
    });}

    @Test
    public void serviceDeletePlanetFailure() {
        Mockito.when(dao.deletePlanet(Mockito.any())).thenReturn(false);

        try {
            Object result = service.deletePlanet(invalidInput);
            if (result instanceof String) {
                fail("Service method deletePlanet() should return boolean");
            }
        } catch (Exception e) {
            if (invalidInput instanceof Planet) {
                assertEquals("Invalid planet name", e.getMessage());
            } else if (invalidInput instanceof String) {
                assertEquals("Invalid planet name", e.getMessage());
            }
        }
    }

}
