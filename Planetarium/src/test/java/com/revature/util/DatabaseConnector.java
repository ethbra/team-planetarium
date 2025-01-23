package com.revature.util;

import com.revature.planetarium.utility.AppConfig;
import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    public static Connection getConnection() throws SQLException {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);
        String url = AppConfig.DATABASE_URL;

        if (url.startsWith("jdbc:sqlite:")) return DriverManager.getConnection(url, config.toProperties());

        DriverManager.registerDriver(new org.postgresql.Driver());

        return DriverManager.getConnection(url, AppConfig.DATABASE_USERNAME, AppConfig.DATABASE_PASSWORD);
    }
}
