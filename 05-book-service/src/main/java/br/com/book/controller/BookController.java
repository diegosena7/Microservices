package br.com.book.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.book.model.Book;
import br.com.book.repository.BookRepository;

@RestController
@RequestMapping("book-service")
public class BookController {
	
	@Autowired private Environment environment;
	@Autowired private BookRepository repository;
	
	Book book = new Book();
	
	@GetMapping(value = "/{id}/{currency}")
	public Book findBooks(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
		
		book = repository.getById(id);
		
		if (book == null) {
			throw new RuntimeException("Book not found");
		}
		
		String port = environment.getProperty("local.server.port");
		
//		book.setAuthor("Diego Sena");
//		book.setTitle("Java OO");
//		book.setLaunchDate(new Date());
//		book.setId(1L);
//		book.setPrice(Double.valueOf(35.9));
//		book.setCurrency(currency);
		book.setEnvironment(port);
		
		return book;
		
	}

}