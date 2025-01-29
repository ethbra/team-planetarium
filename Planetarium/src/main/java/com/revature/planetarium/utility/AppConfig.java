package com.revature.planetarium.utility;

import java.util.HashMap;
import java.util.Map;

import com.revature.planetarium.exceptions.ConfigurationFail;
import org.postgresql.Driver;

public class AppConfig {

    public static String DATABASE_URL = System.getenv("DATABASE_URL");
    public static String PLANETARIUM_URL = System.getenv("PLANETARIUM_URL");
    public static String DATABASE_USERNAME = System.getenv("DATABASE_USERNAME");
    public static String DATABASE_PASSWORD = System.getenv("DATABASE_PASSWORD");
    private static final Map<String, String> configProperties = new HashMap<>();
    public static final Driver postgresDriver = new org.postgresql.Driver();
    static {
        configProperties.put("--database-url", "DATABASE_URL");
        configProperties.put("--planetarium-url", "PLANETARIUM_URL");
        configProperties.put("--database-username", "DATABASE_USERNAME");
        configProperties.put("--database-password", "DATABASE_PASSWORD");
    }

    public static void configureAppProperties(String[] args) throws ConfigurationFail {
        if (args.length > 0) {
            if(args.length % 2 == 0){
                for (int i = 0; i < args.length; i++) {
                    String arg = args[i];
                    if (configProperties.containsKey(arg)) {
                        String propertyName = configProperties.get(arg);
                        switch (propertyName) {
                            case "DATABASE_URL":
                                System.out.println("Setting database URL to " + args[i + 1]);
                                DATABASE_URL = args[++i];
                                break;
                            case "PLANETARIUM_URL":
                                System.out.println("Setting planetarium URL to " + args[i + 1]);
                                PLANETARIUM_URL = args[++i];
                                break;
                            case "DATABASE_USERNAME":
                                DATABASE_USERNAME = args[++i];
                                break;
                            case "DATABASE_PASSWORD":
                                DATABASE_PASSWORD = args[++i];
                                break;
                        }
                    } else {
                        throw new ConfigurationFail("Invalid argument: " + arg);
                    }
                }
            } else {
                throw new ConfigurationFail("Invalid number of command line arguments");
            }
        }
    }
    
}
