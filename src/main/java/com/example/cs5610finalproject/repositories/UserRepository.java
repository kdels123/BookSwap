package com.example.cs5610finalproject.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.cs5610finalproject.model.User;


public interface UserRepository extends CrudRepository<User, Integer> {

}
