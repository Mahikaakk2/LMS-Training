package com.example.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {

		return routeLocatorBuilder
				.routes()
				.route(p -> p
						.path("/lms/employee/**")
						.filters(f -> f.rewritePath("/lms/employee/(?<segment>.*)", "/${segment}"))
						.uri("lb://EMPLOYEE")
				)
				.route(p -> p
						.path("/lms/courses/**")
						.filters(f -> f.rewritePath("/lms/courses/(?<segment>.*)", "/${segment}"))
						.uri("lb://COURSES")
				)
				.route(p -> p
						.path("/lms/queries/**")
						.filters(f -> f.rewritePath("/lms/queries/(?<segment>.*)", "/${segment}"))
						.uri("lb://QUERIES")
				)
		.route(p -> p
				.path("/lms/enrollments/**")
				.filters(f -> f.rewritePath("/lms/enrollments/(?<segment>.*)", "/${segment}"))
				.uri("lb://ENROLLMENTS")
		)
				.build();



	}
	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

}
