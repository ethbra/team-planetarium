package com.revature.planetarium.controller;

import java.util.List;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.planetarium.service.planet.PlanetService;

import io.javalin.http.Context;

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
        List<Planet> planets = planetService.selectByOwner(ownerId);
        ctx.json(planets);
        ctx.status(200);
    }

    public void findByIdentifier(Context ctx) {
        try {
            String identifier = ctx.pathParam("identifier");
            Planet planet;
            if(identifier.matches("^[0-9]+$")) {
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
        try {
            Planet planet = ctx.bodyAsClass(Planet.class);
            Planet createdPlanet = planetService.createPlanet(planet);
            ctx.json(createdPlanet);
            ctx.status(201);            
        } catch (PlanetFail e) {
            ctx.result(e.getMessage());
            ctx.status(400);
        }

    }

    public void updatePlanet(Context ctx){
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
            String responseMessage;
            if(identifier.matches("^[0-9]+$")) {
                responseMessage = planetService.deletePlanet(Integer.parseInt(identifier));
            } else {
                responseMessage = planetService.deletePlanet(identifier);
            }
            ctx.json(responseMessage);
            ctx.status(200);
        } catch (PlanetFail e) {
            ctx.result(e.getMessage());
            ctx.status(400);
        }
    }

}
