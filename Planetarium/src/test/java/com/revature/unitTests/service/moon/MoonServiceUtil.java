package com.revature.unitTests.service.moon;

import com.revature.Setup;
import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.repository.moon.MoonDaoImp;
import com.revature.planetarium.service.moon.MoonService;
import com.revature.planetarium.service.moon.MoonServiceImp;
import org.junit.Before;
import org.mockito.Mockito;

public class MoonServiceUtil {

    protected MoonDao moonDao;  
    protected MoonService<Object> moonService;

    @Before
    public void setup() {
        moonDao = Mockito.mock(MoonDaoImp.class);
        moonService = new MoonServiceImp<>(moonDao);
        Setup.resetTestDatabase();
    }
}
