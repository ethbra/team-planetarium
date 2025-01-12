package com.revature.integration.repository.moon.positive;

import com.revature.Setup;
import com.revature.integration.repository.moon.MoonDaoUtil;
import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.repository.moon.MoonDaoImp;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class DaoMoonTest extends MoonDaoUtil {



//TODO: each of these should have sad path tests
//    Nomenclature should go by root name, plus the specified error
//    e.g., "createMoonNameAlreadyExists"


    @Before
    public void setUp() throws Exception {
        dao = new MoonDaoImp();
    }


    @Test
    public void createMoonPositive() {
        Optional<Moon> response = dao.createMoon(new Moon(0, "My 1st_Moon-", 1));
        assertTrue(response.isPresent());
        assertEquals("My 1st_Moon-", response.get().getMoonName());
    }

    @Test
    public void readMoonByIdPositive() {
        Optional<Moon> response = dao.readMoon(1);
        assertTrue(response.isPresent());
    }

    @Test
    public void readMoonByNamePositive() {
        Optional<Moon> response = dao.readMoon("Luna");
        assertTrue(response.isPresent());
    }

    @Test
    public void readAllMoonsPositive() {
        List<Moon> response = dao.readAllMoons();
        assertFalse(response.isEmpty());
    }

    @Test
    public void readMoonsByPlanetPositive() {
        List<Moon> response = dao.readMoonsByPlanet(1);
        assertFalse(response.isEmpty());
    }

    @Test
    public void deleteMoonByIdPositive() {
        assertTrue(dao.deleteMoon(1));

        Setup.resetTestDatabase();

    }

    @Test
    public void deleteMoonByNamePositive() {
        assertTrue(dao.deleteMoon("Luna"));

        Setup.resetTestDatabase();
    }

}
