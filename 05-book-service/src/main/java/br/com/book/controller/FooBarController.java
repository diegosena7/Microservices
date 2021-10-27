package br.com.book.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;
/*
 * Nesta classe estamos utilizando o Resilience4j que foi setado no POM da aplicação. 
 */
@RestController
@RequestMapping("book-service")
public class FooBarController {
	
	private Logger logger = LoggerFactory.getLogger(FooBarController.class);
	
	@GetMapping("/foo-bar")
	@Retry(name = "foo-bar")//Número de tentativas de acesso após o erro, config no yml
	public String fooBar() {
		logger.info("Request to foo-bar is received.");
		var response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);
//		return "FooBar!!!";
		return response.getBody();
	}
}
