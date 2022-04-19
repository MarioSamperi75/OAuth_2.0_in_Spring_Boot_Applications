package com.appsdeveloperblog.clients.sociallogin.SocialLoginWebClient.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	
	// only root will be accessible to everyone
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.anyRequest().authenticated()
		.and().oauth2Login()
		.and()
		.logout()
		.logoutSuccessUrl("/") 			// you can redirect the user wherever you want 
		.invalidateHttpSession(true)	// true by default
		.clearAuthentication(true)		// true by default	
		.deleteCookies("JSESSIONID");	//  multiple arguments allowed, just type the name of the cookie
										// ("","",""...)			
		
	}
	

}
