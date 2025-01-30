package com.revature.planetarium.repository.planet;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.planetarium.utility.DatabaseConnector;
import com.revature.planetarium.utility.FileType;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class PlanetDaoImp implements PlanetDao {

    @Override
    public Optional<Planet> createPlanet(Planet planet) {
        if (planet.getPlanetName() == null || planet.getPlanetName().isEmpty()) throw new PlanetFail("Invalid planet name");


        String ftype = "";

        try {
            ftype = FileType.getFileType(planet.imageDataAsByteArray());
        } catch (Exception ignored) {}


        if (!ftype.isEmpty() & !ftype.contains("PNG") & !ftype.contains("ZE")) { // ZE is when a JPG is chosen, for some reason
            throw new PlanetFail("Invalid file type");
        }

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO planets (name, ownerId, galaxy, image) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, planet.getPlanetName());
            stmt.setInt(2, planet.getOwnerId());
            stmt.setString(3, planet.getGalaxy());
            stmt.setBytes(4, planet.imageDataAsByteArray());
            int numRowsChanged = stmt.executeUpdate();
            if (numRowsChanged == 0) throw new MoonFail("Invalid planet name");
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int newPlanetId = rs.getInt(1);
                    planet.setPlanetId(newPlanetId);
                    return Optional.of(planet);
                }
            }
        } catch (SQLException e) {
            throw new PlanetFail("Invalid planet name");
        }
        return Optional.empty();
    }


    @Override
    public Optional<Planet> readPlanet(int id) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM planets WHERE id = ?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Planet planet = new Planet();
                    planet.setPlanetId(rs.getInt("id"));
                    planet.setPlanetName(rs.getString("name"));
                    planet.setOwnerId(rs.getInt("ownerId"));
                    planet.setGalaxy(rs.getString("galaxy"));
                    return Optional.of(planet);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new PlanetFail(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Planet> readPlanet(String name) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM planets WHERE name = ?")) {
                stmt.setString(1, name);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        Planet planet = new Planet();
                        planet.setPlanetId(rs.getInt("id"));
                        planet.setPlanetName(rs.getString("name"));
                        planet.setOwnerId(rs.getInt("ownerId"));
                        planet.setGalaxy(rs.getString("galaxy"));
                        return Optional.of(planet);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new PlanetFail(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Planet> readAllPlanets() {
        List<Planet> planets = new ArrayList<>();
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM planets");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Planet planet = new Planet();
                planet.setPlanetId(rs.getInt("id"));
                planet.setPlanetName(rs.getString("name"));
                planet.setOwnerId(rs.getInt("ownerId"));
                planet.setGalaxy(rs.getString("galaxy"));
                if (rs.getBytes("image") != null) {
                    byte[] imageDataAsBytes = rs.getBytes("image");
                    String imageDataBase64 = Base64.getEncoder().encodeToString(imageDataAsBytes);
                    planet.setImageData(imageDataBase64);
                }
                planets.add(planet);
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new PlanetFail(e.getMessage());
        }
        return planets;
    }

    @Override
    public List<Planet> readPlanetsByOwner(int ownerId) {
        List<Planet> planets = new ArrayList<>();
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM planets WHERE ownerId = ?")) {
            stmt.setInt(1, ownerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Planet planet = new Planet();
                    planet.setPlanetId(rs.getInt("id"));
                    planet.setPlanetName(rs.getString("name"));
                    planet.setOwnerId(rs.getInt("ownerId"));
                    planet.setGalaxy(rs.getString("galaxy"));
                    if (rs.getBytes("image") != null) {
                        byte[] imageDataAsBytes = rs.getBytes("image");
                        String imageDataBase64 = Base64.getEncoder().encodeToString(imageDataAsBytes);
                        planet.setImageData(imageDataBase64);
                    }
                    planets.add(planet);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new PlanetFail(e.getMessage());
        }
        return planets;
    }

    @Override
    public Optional<Planet> updatePlanet(Planet planet) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE planets SET name = ?, ownerId = ?, galaxy = ? WHERE id = ?")) {
            stmt.setString(1, planet.getPlanetName());
            stmt.setInt(2, planet.getOwnerId());
            stmt.setString(3, planet.getGalaxy());
            stmt.setInt(4, planet.getPlanetId());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0 ? Optional.of(planet) : Optional.empty();
        } catch (SQLException e) {
            System.out.println(e);
            throw new PlanetFail(e.getMessage());
        }
    }


    @Override
    public boolean deletePlanet(int id) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM planets WHERE id = ?")) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println(e);
            throw new PlanetFail(e.getMessage());
        }
    }

    @Override
    public boolean deletePlanet(String name) {

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM planets WHERE name = ?")) {
            stmt.setString(1, name);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                return true;
            } else throw new PlanetFail("Invalid planet name");
        } catch (SQLException e) {
            System.out.println(e);
            throw new PlanetFail(e.getMessage());
        }
    }

}
