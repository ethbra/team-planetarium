package com.revature.planetarium.utility;

import java.util.HashMap;
import java.util.Map;

import com.revature.planetarium.exceptions.ConfigurationFail;

public class AppConfig {

    public static String DATABASE_URL = System.getenv("DATABASE_URL");
    public static String PLANETARIUM_URL = System.getenv("PLANETARIUM_URL");
    private static final Map<String, String> configProperties = new HashMap<>();

    static {
        configProperties.put("--database-url", "DATABASE_URL");
        configProperties.put("--planetarium-url", "PLANETARIUM_URL");
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
                                DATABASE_URL = args[++i];
                                break;
                            case "PLANETARIUM_URL":
                                PLANETARIUM_URL = args[++i];
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
