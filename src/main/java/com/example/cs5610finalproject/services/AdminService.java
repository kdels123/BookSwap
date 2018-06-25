package com.example.cs5610finalproject.services;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cs5610finalproject.model.Admin;
import com.example.cs5610finalproject.repositories.AdminRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminService {
	
		@Autowired
		AdminRepository adminRepo;
		
		@PostMapping("/api/user/admin")
		public Admin createAdmin(@RequestBody Admin admin, HttpServletResponse response, HttpSession session) {
			session.setAttribute("currentUser", admin);
			List<Admin> result = (List<Admin>) adminRepo.findByUsername(admin.getUsername());
			if (result.size() > 0) {
				response.setStatus(HttpServletResponse.SC_CONFLICT);
				return null;
			}
			return adminRepo.save(admin);
		}

}

