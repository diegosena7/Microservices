package br.com.book.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
/*
 * Nesta classe estamos utilizando o Resilience4j que foi setado no POM da aplicação. 
 */
@Tag(name="Foobar")
@RestController
@RequestMapping("book-service")
public class FooBarController {
	
	private Logger logger = LoggerFactory.getLogger(FooBarController.class);
	
	//Método usando o retry na chamada
	@Operation(summary = "Foobar")
	@GetMapping("/foo-bar")
	@Retry(name = "foo-bar", fallbackMethod = "fallbackMethod")//Número de tentativas de acesso após o erro, config no yml
	public String fooBar() {
		logger.info("Request to foo-bar is received.");
		var response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);
//		return "FooBar!!!";
		return response.getBody();
	}
	
	public String fallbackMethod(Exception e) {
		return "fallbackMethod-foobar";
	}
	
	//Método usando o circuit breaker na chamada
	@GetMapping("/foo-bar-new")
	@CircuitBreaker(name="default", fallbackMethod = "fallbackMethodNew")
	public String fooBarNew() {
		logger.info("Request to foo-bar-new is received.");
		var response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar-new", String.class);
//		return "FooBar!!!";
		return response.getBody();
	}
	
	public String fallbackMethodNew(Exception e) {
		return "fallbackMethodNew-fooBarNew";
	}
	
	//Método usando o rate limiter na chamada
	@GetMapping("/foo-bar-neww")
	@RateLimiter(name = "default")
	public String fooBarNeww() {
		logger.info("Request to foo-bar-neww is received.");
//		var response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar-new1", String.class);
		return "FooBarNeww!!!";
//		return response.getBody();
	}
	
	public String fallbackMethodNeww(Exception e) {
		return "fallbackMethodNew-fooBarNeww";
	}
}
