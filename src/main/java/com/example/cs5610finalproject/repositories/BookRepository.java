package com.example.cs5610finalproject.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.cs5610finalproject.model.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {
	
	@Query("SELECT b FROM Book b WHERE b.bookId=:bookId")
	Iterable<Book> findByBookId(@Param("bookId") String bookId);

}
