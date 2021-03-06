package com.appsdeveloperblog.ws.clients.photoappwebclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class PhotoAppWebClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoAppWebClientApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	
	// to automatically detect the client configuration we need, due to the current authentication object
	// an access token will be included into every single request
	// not to use when communicating with other 3th party web services, we should send the access token!!
	@Bean
	public WebClient webClient(ClientRegistrationRepository clientRegistrationRepository, 
			OAuth2AuthorizedClientRepository oauth2AuthorizedClientRepository) {
		
		ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 = 
				new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepository, 
						oauth2AuthorizedClientRepository);
		
		oauth2.setDefaultOAuth2AuthorizedClient(true);
		
		return WebClient.builder().apply(oauth2.oauth2Configuration()).build();
	}

}
