package com.revature.planetarium.repository.moon;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.planetarium.utility.DatabaseConnector;
import com.revature.planetarium.utility.FileType;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class MoonDaoImp implements MoonDao {

    @Override
    public boolean createMoon(Moon moon) {
        if (moon.getMoonId() != 0) return false;


        String ftype = "ZE";

        if (this.readAllMoons().stream().anyMatch(dbMoon ->
                dbMoon.getMoonName().equals(moon.getMoonName())
        ))
            throw new MoonFail("Invalid moon name");

        try {
            ftype = FileType.getFileType(moon.imageDataAsByteArray());
        } catch (Exception ignore) {
        }

        if (!ftype.isEmpty() & !ftype.contains("PNG") & !ftype.contains("ZE")) {
            throw new MoonFail("Invalid file type");
        }

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO moons (name, myPlanetId, image) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, moon.getMoonName());
            stmt.setInt(2, moon.getOwnerId());
            stmt.setBytes(3, moon.imageDataAsByteArray());
            int numRowsChanged = stmt.executeUpdate();

            if (numRowsChanged == 0) {
                throw new MoonFail("Invalid moon name");
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int newMoonId = rs.getInt(1);
                    moon.setMoonId(newMoonId);
                    return true;
                }
            }
        } catch (SQLException e) {
            String emsg = e.getMessage();
            switch (emsg) {
                case "[SQLITE_CONSTRAINT_FOREIGNKEY] A foreign key constraint failed (FOREIGN KEY constraint failed)":
                case "ERROR: insert or update on table \"moons\" violates foreign key constraint \"moons_myplanetid_fkey\"\n" +
                             "  Detail: Key (myplanetid)=(-1) is not present in table \"planets\".":
                    throw new MoonFail("Invalid planet ID");
                default:
                    throw new MoonFail("Invalid moon name");

            }
        }

        throw new MoonFail("Invalid moon name");
    }

    @Override
    public Optional<Moon> readMoon(int id) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM moons WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Moon moon = new Moon();
                moon.setMoonId(rs.getInt("id"));
                moon.setMoonName(rs.getString("name"));
                moon.setOwnerId(rs.getInt("myPlanetId"));
                byte[] byteImageData = rs.getBytes("image");
                if (byteImageData != null) {
                    String base64ImageData = Base64.getEncoder().encodeToString(byteImageData);
                    moon.setImageData(base64ImageData);
                }
                return Optional.of(moon);
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new MoonFail(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Moon> readMoon(String name) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM moons WHERE name = ?")) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Moon moon = new Moon();
                moon.setMoonId(rs.getInt("id"));
                moon.setMoonName(rs.getString("name"));
                moon.setOwnerId(rs.getInt("myPlanetId"));
                byte[] byteImageData = rs.getBytes("image");
                if (byteImageData != null) {
                    String base64ImageData = Base64.getEncoder().encodeToString(byteImageData);
                    moon.setImageData(base64ImageData);
                }
                return Optional.of(moon);
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new MoonFail(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Moon> readAllMoons() {
        List<Moon> moons = new ArrayList<>();
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM moons")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Moon moon = new Moon();
                moon.setMoonId(rs.getInt("id"));
                moon.setMoonName(rs.getString("name"));
                moon.setOwnerId(rs.getInt("myPlanetId"));
                byte[] byteImageData = rs.getBytes("image");
                if (byteImageData != null) {
                    String base64ImageData = Base64.getEncoder().encodeToString(byteImageData);
                    moon.setImageData(base64ImageData);
                }
                moons.add(moon);
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new MoonFail(e.getMessage());
        }
        return moons;
    }

    @Override
    public List<Moon> readMoonsByPlanet(int planetId) {
        List<Moon> moons = new ArrayList<>();
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM moons WHERE myPlanetId = ?")) {
            stmt.setInt(1, planetId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Moon moon = new Moon();
                moon.setMoonId(rs.getInt("id"));
                moon.setMoonName(rs.getString("name"));
                moon.setOwnerId(rs.getInt("myPlanetId"));
                byte[] byteImageData = rs.getBytes("image");
                if (byteImageData != null) {
                    String base64ImageData = Base64.getEncoder().encodeToString(byteImageData);
                    moon.setImageData(base64ImageData);
                }
                moons.add(moon);
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new MoonFail(e.getMessage());
        }
        return moons;
    }

    @Override
    public Optional<Moon> updateMoon(Moon moon) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE moons SET name = ?, myPlanetId = ? WHERE id = ?")) {
            stmt.setString(1, moon.getMoonName());
            stmt.setInt(2, moon.getOwnerId());
            stmt.setInt(3, moon.getMoonId());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0 ? Optional.of(moon) : Optional.empty();
        } catch (SQLException e) {
            System.out.println(e);
            throw new MoonFail(e.getMessage());
        }
    }

    @Override
    public boolean deleteMoon(int id) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM moons WHERE id = ?")) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println(e);
            throw new MoonFail(e.getMessage());
        }
    }

    @Override
    public boolean deleteMoon(String name) {
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM moons WHERE name = ?")) {
            stmt.setString(1, name);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted != 0) {
                return true;
            }
            else {
                throw new MoonFail("Invalid moon name");
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new MoonFail(e.getMessage());
        }
    }

}
