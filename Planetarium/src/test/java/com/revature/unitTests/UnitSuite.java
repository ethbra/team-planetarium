package com.revature.unitTests;

import com.revature.unitTests.service.user.negative.ServiceAuthenticateTest;
import com.revature.unitTests.service.user.negative.ServiceCreateUserTest;
import com.revature.unitTests.service.user.positive.ServiceUserTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ServiceUserTest.class,
        ServiceAuthenticateTest.class,
        ServiceCreateUserTest.class
})
public class UnitSuite {


}
