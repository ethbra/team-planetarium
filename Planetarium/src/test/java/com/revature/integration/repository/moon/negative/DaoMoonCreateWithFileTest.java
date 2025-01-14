package com.revature.integration.repository.moon.negative;

import com.revature.integration.repository.moon.MoonDaoUtil;
import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.util.Steps;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class DaoMoonCreateWithFileTest extends MoonDaoUtil {


    @Test
    public void createMoonInvalidImageData() throws IOException {
        Moon invalidMoon = new Moon(0, "ValidMoonName", 1);

        Path path = Paths.get(Steps.appendFile("rick-roll-rick-astley.gif"));
        System.out.println("path = " + path);

        invalidMoon.setImageData(Base64.getEncoder().encodeToString(Files.readAllBytes(path)));

        MoonFail exception = assertThrows(MoonFail.class, () -> dao.createMoon(invalidMoon));
        assertEquals("Invalid file type", exception.getMessage());
    }


}
