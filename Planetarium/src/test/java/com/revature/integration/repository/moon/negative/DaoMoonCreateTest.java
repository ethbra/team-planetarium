package com.revature.integration.repository.moon.negative;

import com.revature.integration.repository.moon.MoonDaoUtil;
import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class DaoMoonCreateTest extends MoonDaoUtil {


    @Test
    public void createMoonInvalidNameCharacter() {
        Moon invalidMoon = new Moon(0, "M@@n", 1);
        MoonFail exception = assertThrows(MoonFail.class, () -> dao.createMoon(invalidMoon));
        assertEquals("Invalid moon name", exception.getMessage());
    }


    @Test
    public void createMoonInvalidNameLength() {
        Moon invalidMoon = new Moon(0, "ThisNameIsOverThirtyCharactersLong", 1);
        MoonFail exception = assertThrows(MoonFail.class, () -> dao.createMoon(invalidMoon));
        assertEquals("Invalid moon name", exception.getMessage());
    }


    @Test
    public void createMoonInvalidNameExisting() {
        Moon invalidMoon = new Moon(1, "Luna", 1);
        MoonFail exception = assertThrows(MoonFail.class, () -> dao.createMoon(invalidMoon));
        assertEquals("Invalid moon name", exception.getMessage());
    }


}
