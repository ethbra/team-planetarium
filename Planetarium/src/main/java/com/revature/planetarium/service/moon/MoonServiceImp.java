package com.revature.planetarium.service.moon;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.repository.planet.PlanetDao;
import com.revature.planetarium.repository.planet.PlanetDaoImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MoonServiceImp<T> implements MoonService<T> {

    private MoonDao moonDao;
    private PlanetDao planetDao; 

    public MoonServiceImp(MoonDao moonDao, PlanetDao planetDao) {
        this.moonDao = moonDao;
        this.planetDao = planetDao;
    }

    public MoonServiceImp(MoonDao moonDao) {
        this(moonDao, new PlanetDaoImp());
    }


    @Override
    public boolean createMoon(Moon moon) {
        if (moon.getMoonName().isEmpty() || moon.getMoonName().length() > 30) {
            throw new MoonFail("Invalid moon name");
        }

        // implement business logic

        Planet parentPlanet = planetDao
            .readPlanet(moon.getOwnerId()) 
            .orElseThrow(() -> new MoonFail("Invalid planet ID"));
        moon.setGalaxy(parentPlanet.getGalaxy());

        Optional<Moon> existingMoon = moonDao.readMoon(moon.getMoonName());
        if (existingMoon.isPresent()) {
            throw new MoonFail("Invalid moon name");
        }
        boolean newMoon = moonDao.createMoon(moon);

        if (newMoon) return newMoon;

        throw new MoonFail("Invalid planet ID");
    }


    @Override
    public Moon selectMoon(T idOrName) {
        Optional<Moon> moon;
        if (idOrName instanceof Integer) {
            moon = moonDao.readMoon((Integer) idOrName);
        } else if (idOrName instanceof String) {
            moon = moonDao.readMoon((String) idOrName);
        } else {
            throw new MoonFail("Identifier must be an Integer or String");
        }
        if (moon.isPresent()) {
            return moon.get();
        } else {
            throw new MoonFail("Moon not found");
        }
    }

    @Override
    public List<Moon> selectAllMoons() {
        return moonDao.readAllMoons();
    }

    @Override
    public List<Moon> selectByPlanet(int planetId) {
        List<Moon> moons = moonDao.readAllMoons();
        boolean ownerExists = false;
        for (Moon moon : moons) {
            if (moon.getOwnerId() == planetId) {
                ownerExists = true;
            }
        }
        if (!ownerExists) {
            return new ArrayList<>();
        }
        return moonDao.readMoonsByPlanet(planetId);
    }

    @Override
    public Moon updateMoon(Moon moon) {
        Optional<Moon> existingMoon = moonDao.readMoon(moon.getMoonId());
        if (existingMoon.isEmpty()) {
            throw new MoonFail("Moon not found, could not update");
        }
        if (moon.getMoonName().length() < 1 || moon.getMoonName().length() > 30) {
            throw new MoonFail("Moon name must be between 1 and 30 characters, could not update");
        }
        Optional<Moon> moonWithSameName = moonDao.readMoon(moon.getMoonName());
        if (moonWithSameName.isPresent() && moonWithSameName.get().getMoonId() != moon.getMoonId()) {
            throw new MoonFail("Moon name must be unique, could not update");
        }
        Optional<Moon> updatedMoon = moonDao.updateMoon(moon);
        if (updatedMoon.isPresent()) {
            return updatedMoon.get();
        } else {
            throw new MoonFail("Moon update failed, please try again");
        }
    }

    @Override
    public boolean deleteMoon(T idOrName) {

        boolean deleted;
//        delete moon by ID
        if (idOrName instanceof Integer) {

            List<Moon> moons = moonDao.readAllMoons();
            boolean hasMoon = false;

//            Look for moon with ID idOrName
            for (Moon moon : moons) {
                if ((Integer) idOrName == moon.getMoonId()) {
                    hasMoon = true;
                    break;
                }
            }

            if (!hasMoon) {
                throw new MoonFail("Invalid moon ID");
            } else deleted = moonDao.deleteMoon((int) idOrName);

        } else if (idOrName instanceof String) {
            List<Moon> moons = moonDao.readAllMoons();
            boolean hasMoon = false;
            for (Moon moon : moons) {
                if (Objects.equals(moon.getMoonName(), idOrName)) {
                    hasMoon = true;
                    break;
                }
            }

            if (!hasMoon) {
                throw new MoonFail("Invalid moon name");
            } else
                deleted = moonDao.deleteMoon((String) idOrName);

        } else throw new MoonFail("Invalid moon name");

        if (deleted) return true;

        throw new MoonFail("Invalid moon name");
    }

}
