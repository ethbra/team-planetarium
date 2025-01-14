package com.revature;

import com.revature.integration.repository.IntegrationSuite;
import com.revature.unitTests.UnitSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UnitSuite.class,
        IntegrationSuite.class
})
public class FullSuite {
}
