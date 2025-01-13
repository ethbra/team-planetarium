package com.revature.unitTests.service.planet.positive;

import com.revature.planetarium.entities.Planet;
import com.revature.unitTests.service.planet.ServicePlanetUtil;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class ServicePlanetTest extends ServicePlanetUtil {

    @Test
    public void createPlanet() {
        Planet planet = new Planet();
        planet.setPlanetId(0);
        planet.setPlanetName("My_2nd-Planet");
        planet.setOwnerId(1);
        planet.setImageData("");

        Mockito.when(dao.readPlanet("My_2nd-Planet")).thenReturn(Optional.empty());
        Mockito.when(dao.createPlanet(planet)).thenReturn(Optional.of(planet));

        fail("Service method createPlanet() should return boolean");

        try {
            service.createPlanet(planet);
        } catch (Exception e) {
            fail("Service method createPlanet() should not throw an exception");
        }
    }

    @Test
    public void selectByOwner() {
        Planet planet = new Planet();
        planet.setPlanetId(0);
        planet.setPlanetName("My_2nd-Planet");
        planet.setOwnerId(1);

        List<Planet> planets = Arrays.asList(planet);

        Mockito.when(dao.readPlanetsByOwner(1)).thenReturn(planets);


        assertEquals(planets, service.selectByOwner(1));
    }

    @Test
    public void deletePlanet() {

        Mockito.when(dao.deletePlanet("My_2nd-Planet")).thenReturn(true);

        fail("Service method deletePlanet() should return boolean");


        try {
            service.deletePlanet("My_2nd-Planet");
        } catch (Exception e) {
            fail("Service method deletePlanet() should not throw an exception");
        }
    }
}
