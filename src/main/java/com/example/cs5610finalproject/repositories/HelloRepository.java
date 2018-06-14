package com.example.cs5610finalproject.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.cs5610finalproject.model.Hello;

public interface HelloRepository extends CrudRepository<Hello, Integer> {}
