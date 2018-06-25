package com.example.cs5610finalproject.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.cs5610finalproject.model.Giver;

public interface GiverRepository extends CrudRepository<Giver, Integer> {
	
	@Query("SELECT g FROM Giver g WHERE g.username=:username")
	Iterable<Giver> findByUsername(@Param("username") String username);

}
