package com.example.cs5610finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cs5610finalproject.model.Book;
import com.example.cs5610finalproject.repositories.BookRepository;

@RestController
public class BookService {
	
	@Autowired
	BookRepository repository;
	
	@GetMapping("/api/book")
	public Iterable<Book> findAllBooks() {
		return repository.findAll();
	}
	
	

}
