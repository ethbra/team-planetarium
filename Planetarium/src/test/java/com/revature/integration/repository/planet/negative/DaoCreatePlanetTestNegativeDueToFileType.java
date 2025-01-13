package com.revature.integration.repository.planet.negative;

import com.revature.integration.repository.planet.PlanetDaoUtil;
import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.util.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(Parameterized.class)
public class DaoCreatePlanetTestNegativeDueToFileType extends PlanetDaoUtil {

    private final int planetId = 0;

    @Parameter(2)
    public int ownerId;

    @Parameter(0)
    public String planetName;


    @Parameter(1)
    public String imagePath;


    @Parameters
    public static Collection<Object[]> inputs() {
        return Arrays.asList(new Object[][]
                {
                        {"Venus", "rick-roll-rick-astley.gif", 1}
                }
        );
    }

    @Test
    public void createPlanetNegative() throws IOException {
        Planet planet = new Planet();
        planet.setPlanetName(planetName);
        planet.setOwnerId(ownerId);

        Path path = Paths.get(Steps.appendFile(imagePath));
        System.out.println("path = " + path);

        planet.setImageData(Base64.getEncoder().encodeToString(Files.readAllBytes(path)));

        PlanetFail exception = assertThrows(PlanetFail.class, () -> dao.createPlanet(planet));
        assertEquals("Invalid file type", exception.getMessage());

    }


}
