package com.reactive.reactiveclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class ReactiveClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveClientApplication.class, args);
	}

	@Bean
	RouterFunction<ServerResponse> routes(ConHandler conHandler){
		return route(
				GET("/v2/listcolleagues").and(accept(MediaType.APPLICATION_JSON)), conHandler::listCollegaues)
				.andRoute(GET("/v2/colleague/{name}").and(accept(MediaType.APPLICATION_JSON)), conHandler::collegaue)
				.andRoute(POST("/colleague").and(accept(MediaType.APPLICATION_JSON)),conHandler::saveCollegue)
				;
	}

}
