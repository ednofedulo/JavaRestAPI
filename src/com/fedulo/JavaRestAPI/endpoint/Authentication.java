package com.fedulo.JavaRestAPI.endpoint;

import java.util.Base64;

import javax.annotation.security.PermitAll;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fedulo.JavaRestAPI.bean.Credentials;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("/authentication")
public class Authentication {

	@PermitAll
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response authenticateUser(final Credentials credentials) {

		try {
			authenticate(credentials.getUsername(), credentials.getPassword());

			String token = issueToken(credentials.getUsername());
			return Response.ok(token).build();

		} catch (Exception e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}

	private void authenticate(String username, String password) throws Exception {
		
		if(!username.equals("allowedUser") || !password.equals("userPassword"))
			throw new Exception();
	}

	private String issueToken(String username) {

		byte[] encoded_key = Base64.getDecoder().decode("amF2YXJlc3RhcGk=");
    	SecretKey key = new SecretKeySpec(encoded_key, 0, encoded_key.length, "AES"); 

		String compactJws = Jwts.builder()
		  .setSubject(username)
		  .signWith(SignatureAlgorithm.HS512, key)
		  .compact();
		
		return compactJws;
	}

}
