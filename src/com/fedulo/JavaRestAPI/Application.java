package com.fedulo.JavaRestAPI;

import org.glassfish.jersey.server.ResourceConfig;

import com.fedulo.JavaRestAPI.provider.AuthenticationFilter;
import com.fedulo.JavaRestAPI.provider.GsonMessageBodyHandler;

public class Application extends ResourceConfig {

	public Application(){
		packages("com.fedulo.JavaRestAPI");
		register(GsonMessageBodyHandler.class);
		register(AuthenticationFilter.class);
	}
}
