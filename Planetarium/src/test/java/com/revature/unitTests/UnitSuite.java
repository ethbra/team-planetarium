package com.revature.unitTests;

import com.revature.unitTests.service.planet.positive.ServicePlanetTest;
import com.revature.unitTests.service.user.positive.ServiceUserTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ServiceUserTest.class,
        ServicePlanetTest.class
})
public class UnitSuite {


}
