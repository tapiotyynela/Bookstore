package com.example.Bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Long> {

	List<Book> findByIsbn(String Isbn);
	Book findBooksById(Long id);
	
}
