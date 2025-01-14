package com.revature.integration.repository;

import com.revature.integration.repository.moon.negative.DaoCreateMoonTestNegativeDueToFileType;
import com.revature.integration.repository.moon.negative.DaoCreateMoonTestNegativeDueToName;
import com.revature.integration.repository.moon.negative.DaoDeleteMoonNegative;
import com.revature.integration.repository.moon.negative.DaoReadMoonsByPlanetNegative;
import com.revature.integration.repository.moon.positive.DaoMoonTest;
import com.revature.integration.repository.planet.negative.DaoCreatePlanetTestNegativeDueToName;
import com.revature.integration.repository.planet.negative.DaoDeletePlanetNegative;
import com.revature.integration.repository.planet.negative.DaoReadPlanetsByOwnerNegative;
import com.revature.integration.repository.planet.positive.DaoPlanetTest;
import com.revature.integration.repository.user.negative.DaoCreateUserTest;
import com.revature.integration.repository.user.negative.DaoFindUserTest;
import com.revature.integration.repository.user.positive.DaoUserTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import static org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        DaoUserTest.class,
        DaoFindUserTest.class,
        DaoCreateUserTest.class,
        DaoPlanetTest.class,
        DaoCreateMoonTestNegativeDueToFileType.class,
        DaoCreatePlanetTestNegativeDueToName.class,
        DaoDeletePlanetNegative.class,
        DaoReadPlanetsByOwnerNegative.class,
        DaoMoonTest.class,
        DaoCreateMoonTestNegativeDueToFileType.class,
        DaoCreateMoonTestNegativeDueToName.class,
        DaoDeleteMoonNegative.class,
        DaoReadMoonsByPlanetNegative.class





})
public class IntegrationSuite {

}