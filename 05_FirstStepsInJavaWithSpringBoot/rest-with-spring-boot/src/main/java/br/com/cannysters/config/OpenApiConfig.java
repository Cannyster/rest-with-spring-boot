package br.com.cannysters.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI constomOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("RESTful API with Java and Spring Boot 3")
						.version("v1")
						.description("some description about your API")
						.termsOfService("links")
						.license( 
								new License()
								.name("Apache 2.0")
								.url("Links"))
						);
	}
	
	
}
