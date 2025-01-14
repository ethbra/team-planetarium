package com.revature.integration.repository.moon.negative;

import com.revature.integration.repository.moon.MoonDaoUtil;
import com.revature.planetarium.exceptions.MoonFail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class DaoMoonDeleteTest extends MoonDaoUtil {

    @Parameterized.Parameter(0)
    public String moonName;

    @Parameterized.Parameters
    public static Collection<Object[]> inputs() {
        return Arrays.asList(new Object[][]{
                {"Lina"},
                {"M@@n"},
                {"ThisNameIsOverThirtyCharactersLong"},
        });
    }
    @Test
    public void deleteMoonNegative()
    {
        MoonFail e = Assert.assertThrows(
                MoonFail.class,
                () -> dao.deleteMoon(moonName)
        );

        assertEquals("Invalid moon name", e.getMessage());
    }
}
