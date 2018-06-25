package com.example.cs5610finalproject.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.cs5610finalproject.model.Receiver;

public interface ReceiverRepository extends CrudRepository<Receiver, Integer> {
	
	@Query("SELECT r FROM Receiver r WHERE r.username=:username")
	Iterable<Receiver> findByUsername(@Param("username") String username);


}
