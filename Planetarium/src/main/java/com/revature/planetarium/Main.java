package com.revature.planetarium;

import com.revature.planetarium.exceptions.ConfigurationFail;
import com.revature.planetarium.utility.AppConfig;
import com.revature.planetarium.utility.JavalinSetup;

import io.javalin.Javalin;

public class Main {

	public static void main(String[] args) {
		try {
			AppConfig.configureAppProperties(args);
			Javalin app = Javalin.create(config ->{
				config.bundledPlugins.enableCors(cors -> {
					cors.addRule(it -> {
						it.anyHost();
					});
				});
				config.bundledPlugins.enableDevLogging();
			});
			JavalinSetup.mapRoutes(app);
			app.start(8080);	
		} catch (ConfigurationFail e) {
			e.printStackTrace();
		}
	}
}
