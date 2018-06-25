package com.example.cs5610finalproject.services;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cs5610finalproject.model.Receiver;
import com.example.cs5610finalproject.repositories.ReceiverRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReceiverService {
			
	@Autowired
	ReceiverRepository receiverRepo;
			
	@PostMapping("/api/user/receiver")
	public Receiver createReceiver(@RequestBody Receiver receiver, HttpServletResponse response, HttpSession session) {
			session.setAttribute("currentUser", receiver);
			List<Receiver> result = (List<Receiver>) receiverRepo.findByUsername(receiver.getUsername());
			if (result.size() > 0) {
				response.setStatus(HttpServletResponse.SC_CONFLICT);
				return null;
			}
				return receiverRepo.save(receiver);
		}


}

