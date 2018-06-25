package com.example.cs5610finalproject.services;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cs5610finalproject.model.Book;
import com.example.cs5610finalproject.model.User;
import com.example.cs5610finalproject.repositories.BookRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class BookService {
	
	@Autowired
	BookRepository repository;
	
	@GetMapping("/api/book")
	public Iterable<Book> findAllBooks() {
		return repository.findAll();
	}
	
	@PostMapping("/api/book")
	public Book createBook(@RequestBody Book book, HttpServletResponse response) {
		List<Book> result = (List<Book>) repository.findByBookId(book.getBookId());
		if (result.size() > 0) {
			return result.get(0);
		}
		return repository.save(book);
	}
}
