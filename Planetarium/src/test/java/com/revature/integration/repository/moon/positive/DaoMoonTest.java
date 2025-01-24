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

    @Test
    public void createMoonPositive() {
        boolean response = dao.createMoon(new Moon(0, "My 1st_Moon-", 1));
        assertTrue(response);
    }


    @Test
    public void readMoonByIdPositive() {
        Optional<Moon> response = dao.readMoon(1);
        assertTrue(response.isPresent());
    }


    @Test
    public void deleteMoonByNamePositive() {
        assertTrue(dao.deleteMoon("Luna"));
    }
}
