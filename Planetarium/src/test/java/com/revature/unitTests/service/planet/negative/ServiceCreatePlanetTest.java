package com.revature.unitTests.service.planet.negative;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.unitTests.service.planet.ServicePlanetUtil;
import com.revature.util.Steps;
import com.revature.util.TestDataLoader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Collection;
import java.util.Optional;

@RunWith(Parameterized.class)
public class ServiceCreatePlanetTest extends ServicePlanetUtil {

    @Parameter(0)
    public int planetId;

    @Parameter(1)
    public String planetName;

    @Parameter(2)
    public String fileName;

    @Parameter(3)
    public int ownerId;

    @Parameter(4)
    public String errMessage;

    @Parameters
    public static Collection<Object[]> data() throws FileNotFoundException {
        return TestDataLoader.importData("src/test/resources/ServicePlanetSad.csv");
    }

    @Test
    public void serviceCreatePlanetNegatives() throws IOException {
        Planet planet = new Planet();
        planet.setPlanetId(planetId);
        if (planetName.equals("<empty>"))
            planet.setPlanetName("");
        else
            planet.setPlanetName(planetName);
//        getting making putting an image in Base64
        if (fileName != null) {
            Path path = Paths.get(Steps.appendFile(fileName));

            planet.setImageData(Base64.getEncoder().encodeToString(Files.readAllBytes(path)));
        }


        Mockito.when(dao.readPlanet(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(dao.readPlanet("Earth")).thenReturn(Optional.of(planet));

        Mockito.when(dao.createPlanet(planet)).thenReturn(Optional.empty());

        PlanetFail exMessage = Assert.assertThrows(PlanetFail.class, () ->
                service.createPlanet(planet));

        Assert.assertEquals(errMessage, exMessage.getMessage());



    }
}
