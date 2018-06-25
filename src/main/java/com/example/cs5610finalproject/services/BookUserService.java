package com.example.cs5610finalproject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cs5610finalproject.model.Book;
import com.example.cs5610finalproject.model.BookUser;
import com.example.cs5610finalproject.model.Review;
import com.example.cs5610finalproject.model.User;
import com.example.cs5610finalproject.repositories.BookRepository;
import com.example.cs5610finalproject.repositories.BookUserRepository;
import com.example.cs5610finalproject.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class BookUserService {
	
	@Autowired
	BookUserRepository repository;
	
	@Autowired
	BookRepository bookRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@PostMapping("/api/book/{bookId}/user/{userId}")
	public BookUser createBookUser(@PathVariable("bookId") int bookId, @PathVariable("userId") int userId, @RequestBody BookUser bookUser) {
		Optional<Book> bookData = bookRepo.findById(bookId);
		Optional<User> userData = userRepo.findById(userId);
		if (bookData.isPresent() && userData.isPresent()) {
			Book book = bookData.get();
			User user = userData.get();
			bookUser.setBook(book);
			bookUser.setUser(user);
			return repository.save(bookUser);
		}
		return null;
	}
	
	@GetMapping("/api/book/user/{userId}")
	public List<Book> findAllBooksForUser(@PathVariable ("userId") int userId) {
		List<BookUser> bookUserList = new ArrayList<BookUser>();
		List<Book> bookList = new ArrayList<Book>();

		Optional<User> data = userRepo.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			bookUserList = (List<BookUser>) repository.findBookByUser(user);
		}
		for (BookUser bu: bookUserList) {
			bookList.add(bu.getBook());
		}
		return bookList;
	}
	
	@GetMapping("/api/book/{bookId}/user")
	public List<User> findAllUsersForBook(@PathVariable ("bookId") int bookId) {
		List<BookUser> bookUserList = new ArrayList<BookUser>();
		List <User> userList = new ArrayList<User>();
		
		Optional<Book> data = bookRepo.findById(bookId);
		if(data.isPresent()) {
			Book book = data.get();
			bookUserList = (List<BookUser>) repository.findUserByBook(book);
		}
		for(BookUser bu: bookUserList) {
			userList.add(bu.getUser());
		}
		return userList;
	}
}
