package com.gianni.rest;

import org.glassfish.jersey.server.ResourceConfig;

public class RestApplicationConfig extends ResourceConfig {

	public RestApplicationConfig() {
		packages("com.gianni.rest");
		register(JWTTokenNeededFilter.class);
	}
}
