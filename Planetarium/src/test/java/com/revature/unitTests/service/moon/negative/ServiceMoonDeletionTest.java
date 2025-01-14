package com.revature.unitTests.service.moon.negative;

import com.revature.planetarium.exceptions.MoonFail;
import com.revature.unitTests.service.moon.MoonServiceUtil;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class ServiceMoonDeletionTest extends MoonServiceUtil {

    @Test
    public void deleteMoonFailure() {
        String invalidMoonName = "Mars";

        Mockito.when(moonDao.deleteMoon(invalidMoonName)).thenReturn(false);

        MoonFail thrown = assertThrows(MoonFail.class, () -> {
            moonService.deleteMoon(invalidMoonName);
        });

        assertEquals("Invalid moon name", thrown.getMessage());
    }
}
