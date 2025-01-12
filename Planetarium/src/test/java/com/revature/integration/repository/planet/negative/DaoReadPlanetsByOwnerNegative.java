package com.revature.integration.repository.planet.negative;
import com.revature.integration.repository.planet.PlanetDaoUtil;
import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.entities.Planet;
import com.revature.util.Steps;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class DaoReadPlanetsByOwnerNegative extends PlanetDaoUtil{


   @Test
    public void readPlanetsByOwnerNegativeTest(){
        List<Planet> response = dao.readPlanetsByOwner(0);
        assertTrue(response.isEmpty());

    }


}
