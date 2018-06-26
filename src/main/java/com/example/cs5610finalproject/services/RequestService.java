package com.example.cs5610finalproject.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cs5610finalproject.model.Receiver;
import com.example.cs5610finalproject.model.Giver;
import com.example.cs5610finalproject.model.Request;
import com.example.cs5610finalproject.model.User;
import com.example.cs5610finalproject.repositories.GiverRepository;
import com.example.cs5610finalproject.repositories.RequestRepository;
import com.example.cs5610finalproject.repositories.UserRepository;
import com.example.cs5610finalproject.repositories.ReceiverRepository;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class RequestService {
	
	@Autowired
	RequestRepository repository;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	GiverRepository giverRepo;
	
	@Autowired
	ReceiverRepository receiverRepo;
	
	@PostMapping("/api/giver/{giverId}/receiver/{receiverId}")
	public Request createRequest(@PathVariable("giverId") int giverId, @PathVariable("receiverId") int receiverId, @RequestBody Request request) {
		
		Optional<User> giverData = userRepo.findById(giverId);
		Optional<User> receiverData = userRepo.findById(receiverId);
		if (giverData.isPresent() && receiverData.isPresent()) {
			Giver giver = (Giver) giverData.get();
			Receiver receiver = (Receiver) receiverData.get();
			request.setGiver(giver);
			request.setReceiver(receiver);
			return repository.save(request);
		}
		return null;
	}

}
