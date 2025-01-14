package com.revature.unitTests;

import com.revature.unitTests.service.planet.negative.ServiceCreatePlanetTest;
import com.revature.unitTests.service.planet.negative.ServiceDeletePlanetTest;
import com.revature.unitTests.service.planet.positive.ServicePlanetTest;
import com.revature.unitTests.service.user.positive.ServiceUserTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ServiceDeletePlanetTest.class
})
public class UnitSuite {

}
