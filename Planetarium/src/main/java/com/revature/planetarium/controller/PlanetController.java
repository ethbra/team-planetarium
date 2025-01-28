package com.revature.planetarium.controller;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.planetarium.service.planet.PlanetService;
import io.javalin.http.Context;

import java.util.List;
import java.util.Map;

public class PlanetController {

    private PlanetService planetService;

    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    public void findAll(Context ctx) {
        List<Planet> planets = planetService.selectAllPlanets();
        ctx.json(planets);
        ctx.status(200);
    }

    public void findAllByOwner(Context ctx) {
        int ownerId = Integer.parseInt(ctx.pathParam("ownerId"));
        if (((User) ctx.sessionAttribute("user")).getId() == ownerId) {
            List<Planet> planets = planetService.selectByOwner(ownerId);
            ctx.json(planets);
            ctx.status(200);
        } else
            ctx.status(401);
    }

    public void findByIdentifier(Context ctx) {
        try {
            String identifier = ctx.pathParam("identifier");
            Planet planet;
            if (identifier.matches("^[0-9]+$")) {
                planet = planetService.selectPlanet(Integer.parseInt(identifier));
            } else {
                planet = planetService.selectPlanet(identifier);
            }
            ctx.json(planet);
            ctx.status(200);
        } catch (PlanetFail e) {
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    }

    public void createPlanet(Context ctx) {
        User reqUser = ctx.cachedSessionAttribute("user");
        try {
            Planet planet = ctx.bodyAsClass(Planet.class);

            if (reqUser.getId() != planet.getOwnerId()) {
//               Creating other user's planet not allowed
                ctx.status(400);
                return;
            }

            boolean createdPlanet = planetService.createPlanet(planet);
            if (createdPlanet) {
                ctx.status(201);
            }
        } catch (PlanetFail e) {
            ctx.result(e.getMessage());
            ctx.status(400);
        }
    }

    public void updatePlanet(Context ctx) {
        try {
            Planet planet = ctx.bodyAsClass(Planet.class);
            Planet updatedPlanet = planetService.updatePlanet(planet);
            ctx.json(updatedPlanet);
            ctx.status(200);
        } catch (PlanetFail e) {
            ctx.result(e.getMessage());
            ctx.status(400);
        }

    }

    public void deletePlanet(Context ctx) {
        try {
            String identifier = ctx.pathParam("identifier");
            int ownerId = ((User) ctx.sessionAttribute("user")).getId();

            if (planetService.selectByOwner(ownerId).stream().anyMatch(o ->
                    ((Planet) o).getOwnerId() == ownerId)) {

                if (planetService.deletePlanet(identifier))
                    ctx.status(204);
                else ctx.status(404);

            }
        } catch (PlanetFail e) {
            ctx.status(404).json(Map.of("message", e.getMessage()));
        }
    }

}
