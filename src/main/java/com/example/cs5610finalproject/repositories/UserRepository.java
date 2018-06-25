package com.example.cs5610finalproject.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.cs5610finalproject.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	@Query("SELECT u FROM User u WHERE u.username=:username")
	Iterable<User> findByUsername(@Param("username") String username);
	
	@Query("SELECT u FROM User u WHERE u.username=:username AND u.password=:password")
	Iterable<User> findUserByCredentials(
		@Param("username") String username, 
		@Param("password") String password);

}
