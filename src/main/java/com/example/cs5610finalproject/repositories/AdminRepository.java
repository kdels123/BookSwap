package com.example.cs5610finalproject.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.cs5610finalproject.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer> {

	@Query("SELECT a FROM Admin a WHERE a.username=:username")
	Iterable<Admin> findByUsername(@Param("username") String username);
	
}
