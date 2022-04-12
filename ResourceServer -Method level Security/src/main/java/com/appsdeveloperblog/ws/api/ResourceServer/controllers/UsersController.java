package com.appsdeveloperblog.ws.api.ResourceServer.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}