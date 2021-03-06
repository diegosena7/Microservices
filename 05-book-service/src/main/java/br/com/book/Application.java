package br.com.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients //habilita o Feign na aplicação, usamos o mesmo para criar clients HTTP e consumir os serviços
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
