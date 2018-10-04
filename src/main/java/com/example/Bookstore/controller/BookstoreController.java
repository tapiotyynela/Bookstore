package com.example.Bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.CategoryRepository;



@Controller
public class BookstoreController {
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;

	 @RequestMapping(value="/booklist", method = RequestMethod.GET)
	    public String bookList(Model model) {	
	        model.addAttribute("books", repository.findAll());
	        return "booklist";
}
	 @RequestMapping(value = "/addbook")
	    public String addBook(Model model){
	    	model.addAttribute("book", new Book());
	    	model.addAttribute("categories", crepository.findAll());
	        return "addbook";
	    }     
	    
	    @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String save(Book book){
	        repository.save(book);
	        return "redirect:booklist";
	    }    

	    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	    public String deleteBook(@PathVariable("id") Long Id, Model model) {
	    	repository.deleteById(Id);
	        return "redirect:../booklist";
	    }
	    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	    public String editBook(@PathVariable("id") Long Id, Model model) {
	    	Book book = repository.findBooksById(Id);
	    	model.addAttribute("book", book);
	    	model.addAttribute("categories", crepository.findAll());
	        return "modifybook";
	    }
	    
	    
	    @RequestMapping(value="/books", method = RequestMethod.GET)
	    public @ResponseBody List<Book> studentListRest() {	
	        return (List<Book>) repository.findAll();
	    }    

	    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
	    public @ResponseBody Optional<Book> findStudentRest(@PathVariable("id") Long bookId) {	
	    	return repository.findById(bookId);
	    }       
}
