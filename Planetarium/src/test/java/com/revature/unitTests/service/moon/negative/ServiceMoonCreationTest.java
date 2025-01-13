package com.revature.unitTests.service.moon.negative;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.unitTests.service.moon.MoonServiceUtil;
import com.revature.util.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(Parameterized.class)
public class ServiceMoonCreationTest extends MoonServiceUtil {

    @Parameter(0)
    public String moonName;

    @Parameter(1)
    public int ownerId;

    @Parameter(2)
    public String imageData;

    @Parameter(3)
    public String expectedMessage;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            //moonName                            ownerId  imageData                    expectedMessage
            { "",                                 1,       null,                        "Invalid moon name" },
            { "ThisMoonHasTooManyCharactersHere", 1,       null,                        "Invalid moon name" },
            { "404 M**n N*t F()und!",             1,       null,                        "Invalid moon name" },
            { "Luna",                             1,       null,                        "Invalid moon name" },
            { "My 1st_Moon-",                     -99999,  null,                        "Invalid planet ID" },
            { "My 1st _Moon-",                    1,       "rick-roll-rick-astley.gif", "Invalid file type" }});
    }

    @Test
    public void createMoonFailure() throws IOException {
        Moon invalidMoon = new Moon(0, moonName, ownerId);

        if (imageData != null) {
            Path path = Paths.get(Steps.appendFile(imageData));
            invalidMoon.setImageData(Base64.getEncoder().encodeToString(Files.readAllBytes(path)));
        }

        if ("Luna".equals(moonName)) {
            Mockito.when(moonDao.readMoon("Luna")).thenReturn(Optional.of(new Moon(1, "Luna", 1)));
        } else {
            Mockito.when(moonDao.readMoon(moonName)).thenReturn(Optional.empty());
        }

        Mockito.when(moonDao.createMoon(invalidMoon)).thenReturn(Optional.of(invalidMoon));

        MoonFail thrown = assertThrows(MoonFail.class, () -> {
            moonService.createMoon(invalidMoon);
        });

        assertEquals(expectedMessage, thrown.getMessage());
    }
}
