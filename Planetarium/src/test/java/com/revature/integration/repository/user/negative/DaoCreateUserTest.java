package com.revature.integration.repository.user.negative;

import com.revature.integration.repository.user.UserDaoUtil;
import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.UserFail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(Parameterized.class)
public class DaoCreateUserTest extends UserDaoUtil {

    @Parameter(0)
    public String username;

    @Parameter(1)
    public String password;

    @Parameter(2)
    public int id;

    @Parameter(3)
    public String errorMessage;

    @Parameters
    public static Collection<Object> inputs() {
        return Arrays.asList(new Object[][]
                {{"Batman", "Krypton-was_2000", 0, "Invalid username"},
                        {"Bane", "Krypton-was_2000", 0, "Invalid username"},
                        {"wonder_woman_for_the_DC_theming", "Krypton-was_2000", 0, "Invalid username"},
                        {"2face", "Krypton-was_2000", 0, "Invalid username"},
                        {"joker!!!!!!?)", "Krypton-was_2000", 0, "Invalid username"},
                        {"Super_man-2001", "B4ts", 0, "Invalid password"},
                        {"Super_man-2001", "ThisPhraseIsThirtyOneCharacters", 0, "Invalid password"},
                        {"Super_man-2001", "3atman", 0, "Invalid password"},
                        {"Super_man-2001", "AlfredIsTheBestButlerToExist", 0, "Invalid password"},
                        {"Super_man-2001", "batman1", 0, "Invalid password"},
                        {"Super_man-2001", "BATMAN1", 0, "Invalid password"},
                        {"Super_man-2001", "Robin", 0, "Invalid password"},
                        {"Super_man-2001", "Krypton-was_2000", 1, "Invalid ID"},
                        {"Super_man-2001", "Krypton-was_2000", 99999, "Invalid ID"}
                }
        );
    }


    @Test
    public void createUserNegative() {

        Exception e = assertThrows(UserFail.class, () ->
                dao.createUser(new User(id, username, password))
        );

        assertEquals(errorMessage, e.getMessage());
    }
}
