package com.example.cs5610finalproject.services;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cs5610finalproject.model.Giver;
import com.example.cs5610finalproject.repositories.GiverRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class GiverService {
	
	@Autowired
	GiverRepository giverRepo;
	
	@PostMapping("/api/user/giver")
	public Giver createGiver(@RequestBody Giver giver, HttpServletResponse response, HttpSession session) {
		session.setAttribute("currentUser", giver);
		List<Giver> result = (List<Giver>) giverRepo.findByUsername(giver.getUsername());
		if (result.size() > 0) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return null;
		}
		return giverRepo.save(giver);
	}

}
