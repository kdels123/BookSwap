package com.example.cs5610finalproject.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cs5610finalproject.model.Book;
import com.example.cs5610finalproject.model.Review;
import com.example.cs5610finalproject.model.User;
import com.example.cs5610finalproject.repositories.BookRepository;
import com.example.cs5610finalproject.repositories.ReviewRepository;
import com.example.cs5610finalproject.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReviewService {
	@Autowired
	ReviewRepository reviewRepo;
	
	@Autowired
	BookRepository bookRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/api/review")
	public Iterable<Review> findAllReviews() {
		return reviewRepo.findAll();
	}
	
	@GetMapping("/api/book/{bookId}/review")
	public List<Review> findAllReviewsForBook(@PathVariable ("bookId") int bookId) {
		Optional<Book> data = bookRepo.findById(bookId);
		if(data.isPresent()) {
			Book book = data.get();
			return book.getReviews();
		}
		return null;
	}
	
	@GetMapping("/api/user/{userId}/review")
	public List<Review> findAllReviewsForUser(@PathVariable ("userId") int userId) {
		Optional<User> data = userRepo.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			return user.getReviews();
		}
		return null;
	}
	
	@PostMapping("/api/book/{bookId}/user/{userId}/review")
	public Review createReview(@PathVariable("bookId") int bookId, @PathVariable("userId") int userId, @RequestBody Review review) {
		Optional<Book> bookData = bookRepo.findById(bookId);
		Optional<User> userData = userRepo.findById(userId);
		if (bookData.isPresent() && userData.isPresent()) {
			Book book = bookData.get();
			User user = userData.get();
			review.setBook(book);
			review.setAuthor(user);
			return reviewRepo.save(review);
		}
		return null;
	}
	
	@DeleteMapping("/api/review/{reviewId}")
	public void deleteReview(@PathVariable("reviewId") int id) {
		reviewRepo.deleteById(id);
	}
	

}
