package com.appsdeveloperblog.ws.clients.photoappwebclient.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.appsdeveloperblog.ws.clients.photoappwebclient.response.AlbumRest;

@Controller
public class AlbumsController {
	
	// to get the JWT access token we need the 1) OAuthclientService (we can just inject it!)
	// and the 2) authentication token
	
	// #1
	@Autowired
	OAuth2AuthorizedClientService oauth2ClientService;
	
//	@Autowired
//	RestTemplate restTemplate;
	
	@Autowired
	WebClient webClient;
	
	@GetMapping("/albums")
	// Oidcuser object:  all the info about the open id connect provider
	// You can get the authentication token from the authentication object
	// or from the SecurityContextHolder
	
	//#2
	public String getAlbums (Model model, @AuthenticationPrincipal OidcUser principal) {	

		
		//albums FROM GATEWAY
		String url = "http://localhost:8082/albums";
		
		// webClient object to fetch the list of albums
		List<AlbumRest> albums = webClient.get()
				.uri(url)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<AlbumRest>>(){})
				.block();
		
		//to have the list available in the html page we have to add it to the model 
		model.addAttribute("albums", albums);		
		
		// we return the name of the view
		return "albums";
	}

}

