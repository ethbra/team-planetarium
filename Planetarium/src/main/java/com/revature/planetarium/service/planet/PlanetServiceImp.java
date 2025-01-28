package com.revature.planetarium.service.planet;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.planetarium.repository.planet.PlanetDao;
import com.revature.planetarium.utility.FileType;

import java.util.List;
import java.util.Optional;

public class PlanetServiceImp<T> implements PlanetService<T> {

    private PlanetDao planetDao;

    public PlanetServiceImp(PlanetDao planetDao) {
        this.planetDao = planetDao;
    }

    @Override
    public boolean createPlanet(Planet planet) {
        if (planet.getPlanetName().isEmpty() || planet.getPlanetName().length() > 30) {
            throw new PlanetFail("Invalid planet name");
        }

        if (planet.getPlanetId() != 0)
            throw new PlanetFail("Invalid planet ID");

        String res = FileType.getFileType(planet.imageDataAsByteArray());

        if (!(res.equals("ZE") || res.equals("PNG"))) {
            throw new PlanetFail("Invalid file type");
        }

        Optional<Planet> existingPlanet = planetDao.readPlanet(planet.getPlanetName());
        if (existingPlanet.isPresent()) {
            throw new PlanetFail("Invalid planet name");
        }

        Optional<Planet> createdPlanet = planetDao.createPlanet(planet);

        if (createdPlanet.isPresent()) {
            return true;
        } else {
            throw new PlanetFail("Invalid planet name");
        }
    }

    @Override
    public Planet selectPlanet(T idOrName) {
        Optional<Planet> planet;
        if (idOrName instanceof Integer) {
            planet = planetDao.readPlanet((int) idOrName);
        } else if (idOrName instanceof String) {
            planet = planetDao.readPlanet((String) idOrName);
        } else {
            throw new PlanetFail("identifier must be an Integer or String");
        }
        if (planet.isPresent()) {
            return planet.get();
        } else {
            throw new PlanetFail("Planet not found");
        }
    }

    @Override
    public List<Planet> selectAllPlanets() {
        return planetDao.readAllPlanets();
    }

    @Override
    public List<Planet> selectByOwner(int ownerId) {
        return planetDao.readPlanetsByOwner(ownerId);
    }

    @Override
    public Planet updatePlanet(Planet planet) {
        Optional<Planet> existingPlanet = planetDao.readPlanet(planet.getPlanetId());
        if (existingPlanet.isEmpty()) {
            throw new PlanetFail("Planet not found, could not update");
        }
        if (planet.getPlanetName().isEmpty() || planet.getPlanetName().length() > 30) {
            throw new PlanetFail("Planet name must be between 1 and 30 characters, could not update");
        }
        if (!planet.getPlanetName().equals(existingPlanet.get().getPlanetName())) {
            if (planetDao.readPlanet(planet.getPlanetName()).isPresent()) {
                throw new PlanetFail("Planet name must be unique, could not update");
            }
        }
        Optional<Planet> updatedPlanet = planetDao.updatePlanet(planet);
        if (updatedPlanet.isPresent()) {
            return updatedPlanet.get();
        } else {
            throw new PlanetFail("Planet update failed, please try again");
        }
    }

    @Override
    public boolean deletePlanet(T idOrName) {
        boolean deleted;

//        delete planet by ID
        if (idOrName instanceof Integer) {
            List<Planet> planets = planetDao.readAllPlanets();
            boolean hasPlanet = false;

            for (Planet planet : planets) {
                if ((Integer) idOrName == planet.getPlanetId()) {
                    hasPlanet = true;
                    break;
                }
            }
            if (!hasPlanet) {
                throw new PlanetFail("Invalid planet ID");
            } else deleted = planetDao.deletePlanet((int) idOrName);


        } else if (idOrName instanceof String) {
            List<Planet> planets = planetDao.readAllPlanets();
            System.out.println();
            boolean hasPlanet = false;

            for (Planet planet : planets) {
                if (idOrName.equals(planet.getPlanetName())) {
                    hasPlanet = true;
                    break;
                }
            }
            if (!hasPlanet) {
                throw new PlanetFail("Invalid planet name");
            } else deleted = planetDao.deletePlanet((String) idOrName);
        } else {
            throw new PlanetFail("Invalid planet name");
        }
        if (deleted) {
            return true;
        } else {
            throw new PlanetFail("Invalid planet name");
        }
    }

}
