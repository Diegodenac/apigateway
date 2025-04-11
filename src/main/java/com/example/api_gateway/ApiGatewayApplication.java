package com.example.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ApiGatewayApplication {


	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}


	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route( p -> p
						.path("/all")
						.uri("http://ms-product:9090"))
				.route( p -> p
						.path("/todosmisproductos")
						.filters( f-> f.rewritePath("/todosmisproductos", "/all"))
						.uri("http://ms-product:9090"))
				.route(p -> p
						.path("/get")
						.filters(f -> f.addRequestHeader("Hello", "World"))
						.uri("http://httpbin.org:80"))
				.route(p -> p
						.path("/crear-producto")
						.filters(f -> f.rewritePath("/crear-producto", "/"))
						.uri("http://ms-product:9090"))
				.build();
	}
}
