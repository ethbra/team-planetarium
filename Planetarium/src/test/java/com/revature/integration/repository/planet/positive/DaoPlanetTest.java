package com.revature.integration.repository.planet.positive;

import com.revature.integration.repository.planet.PlanetDaoUtil;
import com.revature.planetarium.entities.Planet;
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
import java.util.Optional;

@RunWith(Parameterized.class)
public class DaoPlanetTest extends PlanetDaoUtil {

    private final int planetId = 0;

    @Parameter(0)
    public String planetName;

    @Parameter(1)
    public String imagePath;

    @Parameter(2)
    public int ownerId;

    @Parameters
    public static Collection<Object[]> inputs() {
        return Arrays.asList(new Object[][]{
                        {"My 1st _Planet-", "planet-1.jpg", 1},
                        {"PN 1", "", 1},
                        {"PN 1", "gearth.png", 1}
                }
        );
    }

    @Test
    public void createPlanetPositive() throws IOException {
        Planet planet = new Planet();
        planet.setPlanetName(planetName);
        planet.setOwnerId(ownerId);

        Path path = Paths.get(Steps.appendFile(imagePath));
        System.out.println("path = " + path);

        if (imagePath.isEmpty()) planet.setImageData("");
        else
            planet.setImageData(Base64.getEncoder().encodeToString(Files.readAllBytes(path)));

        Optional<Planet> response = dao.createPlanet(planet);

        assert (response.isPresent());
    }

    @Test
    public void readAllPlanetsPositive() {
    }

    @Test
    public void readPlanetsByOwnerPositive() {
    }

    @Test
    public void deletePlanetByNamePositive() {
    }

}
