package com.revature.planetarium;

import com.revature.planetarium.exceptions.ConfigurationFail;
import com.revature.planetarium.utility.AppConfig;
import com.revature.planetarium.utility.JavalinSetup;

import io.javalin.Javalin;

import java.util.Map;
import java.util.Scanner;

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

			//Error handler for 413
			app.error(413, ctx -> {
				// Override the status code to 400 if needed
				ctx.status(400);
				// Return custom JSON response for large file uploads
				ctx.json(Map.of("message", "Invalid file type"));
			});


			app.start(8080);
		} catch (ConfigurationFail e) {
			e.printStackTrace();
		}
	}

	private static void javafy() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter lines: ");
		StringBuilder builder = new StringBuilder();
		builder.append("{ {\"");
		int count = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.equals("STOP"))
				break;
			builder.append(line);
			if (count % 2 == 1)
				builder.append("\"}, { \"");
			else
				builder.append("\", \"");
			count++;
		}
		builder.append("\"}");
		System.out.println(builder.toString());
	}
}
