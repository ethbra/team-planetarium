package com.revature.planetarium.controller;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.planetarium.service.moon.MoonService;
import io.javalin.http.Context;

import java.util.List;
import java.util.Map;

public class MoonController {

    private MoonService moonService;

    public MoonController(MoonService moonService) {
        this.moonService = moonService;
    }

    public void findAll(Context ctx) {
        List<Moon> moons = moonService.selectAllMoons();
        ctx.json(moons);
        ctx.status(200);
    }

    public void findAllByPlanet(Context ctx) {
        try{
            int ownerId = Integer.parseInt(ctx.pathParam("planetId"));
            List<Moon> moons = moonService.selectByPlanet(ownerId);

            ctx.json(moons).status(200);

        } catch (Exception e) {
            ctx.status(400);
        }
    }

    public void findByIdentifier(Context ctx) {
        try {
            String identifier = ctx.pathParam("identifier");
            Moon moon;
            if (identifier.matches("^[0-9]+$")) {
                moon = moonService.selectMoon(Integer.parseInt(identifier));
            } else {
                moon = moonService.selectMoon(identifier);
            }
            ctx.json(moon);
            ctx.status(200);
        } catch (MoonFail e) {
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    }

    public void createMoon(Context ctx) {
        try {
            Moon moon = ctx.bodyAsClass(Moon.class);

            if (moonService.createMoon(moon)) {
                ctx.json(moon);
                ctx.status(201);
            }
        } catch (MoonFail e) {
            ctx.json(Map.of("message", e.getMessage()))
                    .status(400);
        }
    }

    public void deleteMoon(Context ctx) {
        try {
            String identifier = ctx.pathParam("identifier");
            boolean isDeleted;
            if (identifier.matches("^[0-9]+$")) {
                isDeleted = moonService.deleteMoon(Integer.parseInt(identifier));
            } else {
                isDeleted = moonService.deleteMoon(identifier);
            }
            if (isDeleted) {
                ctx.status(204);
            } else {
                ctx.status(404)
                        .json(Map.of("message", "Invalid moon name"));
            }
        } catch (MoonFail e) {
            ctx.json(Map.of("message", e.getMessage()))
                    .status(404);
        }
    }

}