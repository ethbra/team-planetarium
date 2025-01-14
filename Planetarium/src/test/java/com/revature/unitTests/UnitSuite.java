package com.revature.unitTests;

import com.revature.unitTests.service.moon.negative.ServiceMoonCreationTest;
import com.revature.unitTests.service.moon.negative.ServiceMoonDeletionTest;
import com.revature.unitTests.service.moon.negative.ServiceMoonRetrievalTest;
import com.revature.unitTests.service.moon.positive.ServiceMoonTests;
import com.revature.unitTests.service.planet.negative.ServicePlanetCreateTest;
import com.revature.unitTests.service.planet.negative.ServicePlanetDeleteTest;
import com.revature.unitTests.service.planet.negative.ServicePlanetSelectTest;
import com.revature.unitTests.service.planet.positive.ServicePlanetTest;
import com.revature.unitTests.service.user.negative.ServiceAuthenticateTest;
import com.revature.unitTests.service.user.negative.ServiceCreateUserTest;
import com.revature.unitTests.service.user.positive.ServiceUserTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ServiceMoonCreationTest.class,
        ServiceMoonDeletionTest.class,
        ServiceMoonRetrievalTest.class,
        ServiceMoonTests.class,
        ServicePlanetCreateTest.class,
        ServicePlanetDeleteTest.class,
        ServicePlanetSelectTest.class,
        ServicePlanetTest.class,
        ServiceAuthenticateTest.class,
        ServiceCreateUserTest.class,
        ServiceUserTest.class
})
public class UnitSuite {

}
