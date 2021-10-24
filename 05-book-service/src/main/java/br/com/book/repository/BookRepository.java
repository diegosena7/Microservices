package br.com.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.book.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
