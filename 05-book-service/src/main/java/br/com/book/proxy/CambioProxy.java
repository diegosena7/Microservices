package br.com.book.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.book.response.CambioResponse;

/*
 * Nesta classe estamos abstraindo dinamicamente as informações da URL do serviço que será consumido (cambio-service)
 * Estamos usando o Feign para criar o client HTTP e consumir o serviço
 */
@FeignClient(name = "cambio-service")
public interface CambioProxy {
	
	@GetMapping(value = "/cambio-service/{amount}/{from}/{to}")
	public CambioResponse getCambio(@PathVariable("amount") Double amount, @PathVariable("from") String from, @PathVariable("to") String to);
}
