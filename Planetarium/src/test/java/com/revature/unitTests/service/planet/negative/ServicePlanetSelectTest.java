package com.revature.unitTests.service.planet.negative;

import com.revature.unitTests.service.planet.ServicePlanetUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ServicePlanetSelectTest extends ServicePlanetUtil {

    @Parameter
    public int ownerId;



    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {-1000},
                {9999}
        });
    }

    @Test
    public void serviceSelectPlanetNegatives() {

        Mockito.when(dao.readPlanetsByOwner(Mockito.anyInt())).thenReturn(List.of());

        assertEquals(List.of(), service.selectByOwner(ownerId));

    }
}
