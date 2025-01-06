package com.revature.unitTests.repository.moon;

import com.revature.Setup;
import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.repository.moon.MoonDaoImp;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class MoonDaoPositiveTest extends MoonDaoUtil{



//TODO: each of these should have sad path tests
//    Nomenclature should go by root name, plus the specified error
//    e.g., "createMoonNameAlreadyExists"


    @Before
    public void setUp() throws Exception {
        dao = new MoonDaoImp();
    }


    @Test
    public void createMoon() {
        Optional<Moon> response = dao.createMoon(new Moon(0, "My 1st_Moon-", 1));
        assertTrue(response.isPresent());
        assertEquals("My 1st_Moon-", response.get().getMoonName());
    }

    @Test
    public void readMoonById() {
        Optional<Moon> response = dao.readMoon(1);
        assertTrue(response.isPresent());
    }

    @Test
    public void readMoonByName() {
        Optional<Moon> response = dao.readMoon("Luna");
        assertTrue(response.isPresent());
    }

    @Test
    public void readAllMoons() {
        List<Moon> response = dao.readAllMoons();
        assertFalse(response.isEmpty());
    }

    @Test
    public void readMoonsByPlanet() {
        List<Moon> response = dao.readMoonsByPlanet(1);
        assertFalse(response.isEmpty());
    }

    @Test
    public void deleteMoonById() {
        assertTrue(dao.deleteMoon(1));

        Setup.resetTestDatabase();

    }

    @Test
    public void deleteMoonByName() {
        assertTrue(dao.deleteMoon("Luna"));

        Setup.resetTestDatabase();
    }

}
