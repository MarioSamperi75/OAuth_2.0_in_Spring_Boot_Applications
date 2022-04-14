//configuring a client for this eureka server

// in the resource servere pom file
// 		#1 combine the spring boot version with the appropriate spring cloud version (e.g. 2.6.6. with 2021.0.1)
//		#2 add the spring-cloud-starter-netflix-eureka-client dependency
//		#3 add the spring-cloud-starter-bootstrap dependency
//		#4 add the dependencyManagement

// in the resources
//		#5 add the discovery service in the application.properties
//		#6 create a bootstrap.properties and add the name of the client

//in the resource server application
//		#7 add the @EnableDiscoveryClient


package com.appsdeveloperblog.ws.api.discoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryServiceApplication.class, args);
	}

}
