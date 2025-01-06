package com.revature.unitTests;

import com.revature.unitTests.repository.moon.MoonDaoNegativeTest;
import com.revature.unitTests.repository.moon.MoonDaoPositiveTest;
import com.revature.unitTests.repository.planet.PlanetDaoNegativeTest;
import com.revature.unitTests.repository.planet.PlanetDaoPositiveTest;
import com.revature.unitTests.repository.user.UserDaoNegativeTest;
import com.revature.unitTests.repository.user.UserDaoPositiveTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserDaoNegativeTest.class,
        UserDaoPositiveTest.class,
        MoonDaoNegativeTest.class,
        MoonDaoPositiveTest.class,
        PlanetDaoNegativeTest.class,
        PlanetDaoPositiveTest.class
})
public class UnitSuite {


}
