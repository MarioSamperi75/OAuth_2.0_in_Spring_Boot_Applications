package com.appsdeveloperblog.ws.api.ResourceServer.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
	// @PreAuthorize allows to use method security expression - more powerful!!
	//@PreAuthorize("hasAuthority('ROLE_developer')")
	@PreAuthorize("hasRole('developer')")
	@DeleteMapping(path = "/{id}")
	public String deleteUser (@PathVariable String id) {
		return "Deleted user with id: " + id;
	}
	
}