package br.com.book.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.book.model.Book;
import br.com.book.proxy.CambioProxy;
import br.com.book.repository.BookRepository;
import br.com.book.response.CambioResponse;

@RestController
@RequestMapping("book-service")
public class BookController {
	
	@Autowired private Environment environment;
	@Autowired private BookRepository repository;
	@Autowired private CambioProxy proxy;
	
	Book book = new Book();
	
	//Modo de busca sem o uso do proxy, onde está abstraido os dados da URL do seviço que será consumid0 (cambio-service)
//	@GetMapping(value = "/{id}/{currency}")
//	public Book findBooks(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
//		
//		book = repository.getById(id);
//		
//		if (book == null) throw new RuntimeException("Book not found");
//		
//		HashMap<String, String> params = new HashMap<>();
//		params.put("amount", book.getPrice().toString());
//		params.put("from", "USD");
//		params.put("to", currency);
//		var cambioResponse = new RestTemplate().getForEntity("http://localhost:8001/cambio-service/{amount}/{from}/{to}", CambioResponse.class, params);
//		var cambio = cambioResponse.getBody();
//		
//		String port = environment.getProperty("local.server.port");
//		book.setEnvironment(port);
//		book.setPrice(cambio.getConvertedValue());
//		return book;
//	}
	
	@GetMapping(value = "/{id}/{currency}")
	public Book findBooks(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
		
		book = repository.getById(id);
		
		if (book == null) throw new RuntimeException("Book not found");
		
		var cambio = proxy.getCambio(book.getPrice(), "USD", currency);
		
		String port = environment.getProperty("local.server.port");
		
		book.setEnvironment(port + " FEIGN");
		book.setPrice(cambio.getConvertedValue());
		return book;
		
	}

}