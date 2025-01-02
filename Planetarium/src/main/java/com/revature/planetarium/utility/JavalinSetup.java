package com.revature.planetarium.utility;

import com.revature.planetarium.controller.MoonController;
import com.revature.planetarium.controller.PlanetController;
import com.revature.planetarium.controller.UserController;
import com.revature.planetarium.controller.ViewController;
import com.revature.planetarium.exceptions.AuthenticationFailed;
import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.repository.moon.MoonDaoImp;
import com.revature.planetarium.repository.planet.PlanetDao;
import com.revature.planetarium.repository.planet.PlanetDaoImp;
import com.revature.planetarium.repository.user.UserDao;
import com.revature.planetarium.repository.user.UserDaoImp;
import com.revature.planetarium.service.moon.MoonService;
import com.revature.planetarium.service.moon.MoonServiceImp;
import com.revature.planetarium.service.planet.PlanetService;
import com.revature.planetarium.service.planet.PlanetServiceImp;
import com.revature.planetarium.service.user.UserService;
import com.revature.planetarium.service.user.UserServiceImp;

import io.javalin.Javalin;

public class JavalinSetup {

    final public static UserDao userDao = new UserDaoImp();
    final public static UserService userService = new UserServiceImp(userDao);
    final public static UserController userController = new UserController(userService);

    final public static PlanetDao planetDao = new PlanetDaoImp();
    final public static PlanetService planetService = new PlanetServiceImp(planetDao);
    final public static PlanetController planetController = new PlanetController(planetService);

    final public static MoonDao moonDao = new MoonDaoImp();
    final public static MoonService moonService = new MoonServiceImp(moonDao);
    final public static MoonController moonController = new MoonController(moonService);

    final public static ViewController viewController = new ViewController();

    public static void mapRoutes(Javalin app){

        /*
         * Mapping Authentication and exception handling
         */

        app.before("/planetarium/*", userController::authenticateUser);
        app.before("/planetarium", userController::authenticateUser);
        app.exception(AuthenticationFailed.class, (e, ctx) -> {
            ctx.status(401);
            ctx.result(e.getMessage());
        });

        // for background image
        app.get("/background", viewController::backgroundImage);
        
        /*
         * Mapping Pages to Javalin app
         */

        app.get("/", viewController::login);
        app.get("/register", viewController::register);
        app.get("/planetarium", viewController::home);

        /*
         * Mapping User Routes
         */

        app.post("/login", userController::login);
        app.post("/register", userController::createUser);
        app.post("/logout", userController::logout);

        /*
         * Mapping Planet Routes
         */

        app.get("/planetarium/planet", planetController::findAll);
        app.get("/planetarium/planet/owner/{ownerId}", planetController::findAllByOwner);
        app.get("/planetarium/planet/{identifier}", planetController::findByIdentifier);
        app.post("/planetarium/planet", planetController::createPlanet);
        app.patch("/planetarium/planet", planetController::updatePlanet);
        app.delete("/planetarium/planet/{identifier}", planetController::deletePlanet);

        /*
         * Mapping Moon Routes
         */

        app.get("/planetarium/moon", moonController::findAll);
        app.get("/planetarium/moon/owner/{planetId}", moonController::findAllByPlanet);
        app.get("/planetarium/moon/{identifier}", moonController::findByIdentifier);
        app.post("/planetarium/moon", moonController::createMoon);
        app.delete("/planetarium/moon/{identifier}", moonController::deleteMoon);
    }
    
}
