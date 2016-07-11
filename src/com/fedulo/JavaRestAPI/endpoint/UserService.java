package com.fedulo.JavaRestAPI.endpoint;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/user")
public class UserService {

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public String getUser(@Context HttpServletRequest httpRequest) 
	{
		String username = (String)httpRequest.getAttribute("username");
		
		return username;
	}
}
