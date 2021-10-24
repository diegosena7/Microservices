package br.com.cambio.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cambio.model.Cambio;
import br.com.cambio.repository.CambioRepository;

/*
 * Nesta classe estamos fazendo uma conversão de moeda, sempre de dólar (USD) para outra moeda (BRL, EUR, GBP, ARS, CLP, COP e MXN)
 * Passamos a responsabilidade de conversão para as migrations utilizando o Flyway.
 */
@RestController
@RequestMapping("cambio-service")
public class CambioController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CambioRepository repository;
	
	Cambio cambio = new Cambio ();
	
	@GetMapping(value = "/{amount}/{from}/{to}")
	public Cambio getCambio(@PathVariable("amount") BigDecimal amount, @PathVariable("from") String from, @PathVariable("to") String to) {

		cambio = repository.findByFromAndTo(from, to);
		if (cambio == null) throw new RuntimeException("Currency Unsupported");
		
		String port = environment.getProperty("local.server.port");
		BigDecimal conversionFactor = cambio.getConversionFactor();
		BigDecimal convertedValue = conversionFactor.multiply(amount);
		cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING));
		cambio.setEnvironment(port);
		return cambio;
	}
}