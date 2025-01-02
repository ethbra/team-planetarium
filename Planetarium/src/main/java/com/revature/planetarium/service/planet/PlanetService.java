package com.revature.planetarium.service.planet;

import com.revature.planetarium.entities.Planet;

import java.util.List;

public interface PlanetService<T> {

    Planet createPlanet(Planet planet);
    Planet selectPlanet(T idOrName);
    List<Planet> selectAllPlanets();
    List<Planet> selectByOwner(int ownerId);
    Planet updatePlanet(Planet planet);
    String deletePlanet(T idOrName);
    
}
