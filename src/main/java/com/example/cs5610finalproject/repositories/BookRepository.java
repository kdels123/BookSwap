package com.example.cs5610finalproject.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.cs5610finalproject.model.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {

}
