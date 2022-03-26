//package com.appsdeveloperblog.ws.api.ResourceServer.security;
//
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@EnableWebSecurity
//public class WebSecurity extends WebSecurityConfigurerAdapter{
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
////		http.authorizeRequests((requests) -> requests.anyRequest().authenticated());
////		http.oauth2ResourceServer().jwt();
//		
//		http
//			.authorizeRequests()
//				.antMatchers(HttpMethod.GET, "/users/status/check")
//				.hasAuthority("SCOPE_profile")
//				.anyRequest().authenticated()
//			.and()
//			.oauth2ResourceServer()
//			.jwt();
//	}
//
//}
package com.appsdeveloperblog.ws.api.ResourceServer.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	 
		http
			.authorizeRequests()
					.antMatchers(HttpMethod.GET, "/users/status/check")
					.hasAuthority("SCOPE_profile")
				.anyRequest().authenticated()
				.and()
			.oauth2ResourceServer()
			.jwt();
	}
	
}