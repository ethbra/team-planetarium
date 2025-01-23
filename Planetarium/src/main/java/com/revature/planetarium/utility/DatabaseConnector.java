package com.revature.planetarium.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.stream.Stream;

import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;
import org.sqlite.SQLiteConfig;

public class DatabaseConnector {

    public static Connection getConnection() throws SQLException {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);
        String url = AppConfig.DATABASE_URL;

        if (url.startsWith("jdbc:sqlite:")) return DriverManager.getConnection(url, config.toProperties());

        DriverManager.registerDriver(new org.postgresql.Driver());
        return DriverManager.getConnection(url, AppConfig.DATABASE_USERNAME, AppConfig.DATABASE_PASSWORD);

    }

    public static void resetTestDatabase(Context context) {

        // TODO probably needs you to have "setup-reset.sql" where you downloaded the jar
        Path sql = Path.of("setup-reset.sql");
        StringBuilder sqlBuilder = new StringBuilder();
        try (Connection conn = DatabaseConnector.getConnection(); Stream<String> lines = Files.lines(sql)) {
            conn.setAutoCommit(false);
            lines.forEach(sqlBuilder::append);
            String sqlString = sqlBuilder.toString();
            String[] sqlStatements = sqlString.split(";");
            int imageCount = 1;
            for (String sqlStatement : sqlStatements) {
                if (sqlStatement.contains("?")) {
                    String type = sqlStatement.contains("moons") ? "moon" : "planet";
                    try (PreparedStatement ps = conn.prepareStatement(sqlStatement)) {
                        byte[] imageData = convertImgToByteArray(String.format("src/test/resources/Celestial-Images/%s-%d.jpg", type, imageCount));
                        ps.setBytes(1, imageData);
                        ps.executeUpdate();
                        imageCount = imageCount == 2 ? 1 : 2;
                    }
                } else {
                    try (Statement stmt = conn.createStatement()) {
                        stmt.executeUpdate(sqlStatement);
                    }
                }
            }
            conn.commit();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static byte[] convertImgToByteArray(String filePath) throws IOException {
        return Files.readAllBytes(Paths.get(filePath));
    }

}
