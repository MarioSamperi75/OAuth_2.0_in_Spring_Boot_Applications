package com.appsdeveloperblog.ws.clients.photoappwebclient.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.appsdeveloperblog.ws.clients.photoappwebclient.response.AlbumRest;

@Controller
public class AlbumsController {
	
	// to get the JWT access token we need the 1) OAuthclientService (we can just inject it!)
	// and the 2) authentication token
	
	// #1
	@Autowired
	OAuth2AuthorizedClientService oauth2ClientService;
	
	@GetMapping("/albums")
	// Oidcuser object:  all the info about the open id connect provider
	// You can get the authentication token from the authentication object
	// or from the SecurityContextHolder
	
	//#2
	public String getAlbums (Model model, @AuthenticationPrincipal OidcUser principal, 
			Authentication authentication) {	
		// #2 alternative
		//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		// we just cast it to OAuth2AuthenticationToken
		OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
		
		// now we have 1 and 2 ,we can get the JWC access token
		// we have to get an object of Outh2AutorizedClient, by adding some information from the oauthToken, 
		// and from this object we can get the JWC access token
		
		OAuth2AuthorizedClient oauth2Client = oauth2ClientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName()); 
		String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
		
		System.out.println("JWT Access Token: " +  jwtAccessToken);
		
		
		
		System.out.println("Principal: " + principal);
		OidcIdToken idToken = principal.getIdToken();
		String idTokenValue = idToken.getTokenValue();
		System.out.println("IdTokenValue: " + idTokenValue);
		
		AlbumRest album = new AlbumRest();
		album.setAlbumId("albumOne");
		album.setAlbumTitle("Album One Title");
		album.setAlbumUrl("http://localhost:8082/albums/1");
		
		AlbumRest album2 = new AlbumRest();
		album2.setAlbumId("albumTwo");
		album2.setAlbumTitle("Album Two Title");
		album2.setAlbumUrl("http://localhost:8082/albums/2");
		
		List returnValue = Arrays.asList(album, album2 );
		
		//to have the list available in the html page we have to add it to the model 
		model.addAttribute("albums", returnValue);
		
		
		
		
		
		
		// we return the name of the view
		return "albums";
	}

}
