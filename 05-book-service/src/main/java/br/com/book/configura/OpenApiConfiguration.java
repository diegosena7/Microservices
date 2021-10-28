package br.com.book.configura;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;

@OpenAPIDefinition(info=@Info(title="Book Service API", version="V1", description="Documentation of Book Service API"))
public class OpenApiConfiguration {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().components(new Components()).info(new io.swagger.v3.oas.models.info.Info()
				.title("Book Service API")
				.version("V1")
				.license(new License().name("Microservices"))
				.description("Documentation of Book Service API"));
	}
}
