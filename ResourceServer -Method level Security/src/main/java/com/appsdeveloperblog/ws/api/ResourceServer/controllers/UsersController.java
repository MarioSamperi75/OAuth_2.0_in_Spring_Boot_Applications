package com.appsdeveloperblog.ws.api.ResourceServer.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.ws.api.ResourceServer.response.UserRest;

@RestController
@RequestMapping("/users")
public class UsersController {

	@GetMapping("/status/check")
	public String status() {
		return "Working...";
	}
	
	//@Secured("ROLE_developer")
	//@PreAuthorize allows to use method security expression - more powerful!!¨
	// we can inject the Jwt object (@A@AuthenticationPrincipal)
	// We can compare the path variable with the property sub (subject, user) in the Jwt object
	
	@PreAuthorize("hasRole('developer') or #id == #jwt.subject")
	//@PreAuthorize("hasAuthority('ROLE_developer')")
	@DeleteMapping(path = "/{id}")
	public String deleteUser (@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
		return "Deleted user with id: " + id + "and JWT subject" + jwt.getSubject();
	}
	
	// return object is a key word
	// to secure this method
	// we compare the response id (should be from a db) with the userId in the JWT 
	// the userId in the JWT is the inlogged user (comes from the Bearer)
	// the user in the response is the one who owns the data
	@PostAuthorize("returnObject.userId == #jwt.subject")
	@GetMapping(path = "/{id}")
	public UserRest getUser (@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
		// we have to hard code the userId that we get in the JWT
		return new UserRest("Mario", "Samperi", "1f018bbf-8356-4e48-ae07-54b1aac7e1b");
	}
	
	
}