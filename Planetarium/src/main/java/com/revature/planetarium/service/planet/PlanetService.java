package com.revature.planetarium.service.planet;

import com.revature.planetarium.entities.Planet;

import java.util.List;

public interface PlanetService<T> {

    boolean createPlanet(Planet planet);
    Planet selectPlanet(T idOrName);
    List<Planet> selectAllPlanets();
    List<Planet> selectByOwner(int ownerId);
    Planet updatePlanet(Planet planet);
    boolean deletePlanet(T idOrName);
    
}
