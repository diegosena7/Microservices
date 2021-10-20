package br.com.erudio.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/*
 * Nesta classe estamos obtendo os valores através da classe application.yml
 * a classe mencionada possui o atributo name igual ao valor passado para
 * a anotação @ConfigurationProperties.
 */

@Component
@RefreshScope
@ConfigurationProperties("greeting-service")
public class GreetingConfiguration {

	private String greeting;
	private String defaultValue;
	
	public GreetingConfiguration() {}

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}