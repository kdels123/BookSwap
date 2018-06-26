package com.example.cs5610finalproject.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.cs5610finalproject.model.Book;
import com.example.cs5610finalproject.model.BookUser;
import com.example.cs5610finalproject.model.User;

public interface BookUserRepository extends CrudRepository<BookUser, Integer> {
	
	@Query("SELECT bu FROM BookUser bu WHERE bu.user=:user")
	Iterable<BookUser> findBookByUser(@Param("user") User user);
	
	@Query("SELECT bu FROM BookUser bu WHERE bu.book=:book")
	Iterable<BookUser> findUserByBook(@Param("book") Book book);
	
	@Query("SELECT bu FROM BookUser bu WHERE bu.book=:book AND bu.user=:user")
	Iterable<BookUser> findByCredentials(
		@Param("book") Book book, 
		@Param("user") User user);

}
