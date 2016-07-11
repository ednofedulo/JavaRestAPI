package com.fedulo.JavaRestAPI.provider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Base64;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import io.jsonwebtoken.Jwts;

@Provider
public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter
{
	
	@Context
    private ResourceInfo resourceInfo;
     
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException
    {
        Method method = resourceInfo.getResourceMethod();

        if(method.isAnnotationPresent(PermitAll.class))
        	return;
        
        if(method.isAnnotationPresent(DenyAll.class))
        {
        	requestContext.abortWith( Response.status(Response.Status.UNAUTHORIZED)
        			.entity("You cannot access this resource").build() );
            return;
        }
        
        String authorizationHeader = 
                requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }
        
        String token = authorizationHeader.substring("Bearer".length()).trim();
        
        try {
            validateToken(token, requestContext);
            
        } catch (Exception e) {
            requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
    private void validateToken(final String token, ContainerRequestContext requestContext)
    {
    	byte[] encoded_key = Base64.getDecoder().decode("amF2YXJlc3RhcGk=");
    	SecretKey key = new SecretKeySpec(encoded_key, 0, encoded_key.length, "AES"); 
	    String username = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
	    requestContext.setProperty("username", username);
    }
}