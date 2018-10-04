package com.example.Bookstore;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;

import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.Book;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository crepository) {
		return new CommandLineRunner () {
			@Override
			public void run(String... args) throws Exception {
			log.info("save a couple of books");
			crepository.save(new Category("IT"));
			crepository.save(new Category("Business"));
			crepository.save(new Category("Law"));
			
			repository.save(new Book("sdsdsd", "sdsdsd", "sdsdsd", "wewewe", 30, crepository.findByName("IT").get(0)));
			repository.save(new Book("Katy", "Kateson", "kate@kate.com", "wewewe", 30, crepository.findByName("Business").get(0)));	
			
			log.info("fetch all books");
			for (Book book: repository.findAll()) {
				log.info(book.toString());
			}}

		};
	}
}
