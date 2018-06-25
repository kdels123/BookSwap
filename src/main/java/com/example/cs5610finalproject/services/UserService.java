package com.example.cs5610finalproject.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cs5610finalproject.model.User;
import com.example.cs5610finalproject.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	private HttpSession session;
	
	@GetMapping("/api/users")
	public Iterable<User> findAllUsers() {
		return repository.findAll();
	}
	
	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int userId) {
		Optional<User> data = repository.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			return user;
		}
		return null;
	}
	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user, HttpServletResponse response) {
		List<User> result = (List<User>) repository.findByUsername(user.getUsername());
		if (result.size() > 0) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return null;
		}
		return repository.save(user);
	}
	
	@PostMapping("/api/user/login")
	@ResponseBody
	public List<User> login(@RequestBody User user, HttpServletResponse response, HttpSession session) {
		List<User> result = (List<User>) repository.findUserByCredentials(user.getUsername(), user.getPassword());
		if (!(result.size() > 0)) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return null;
		}
		
		session.setAttribute("currentUser", result.get(0));
		this.session = session;
		System.out.println(this.session);
		return result;
	}
	
	@PostMapping("/api/user/logout")
	public void logout() {
		this.session.invalidate();
	}
	
	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) {
		Optional<User> data = repository.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setEmail(newUser.getEmail());
			user.setAddress(newUser.getAddress());
			user.setCity(newUser.getCity());
			user.setState(newUser.getState());
			
			this.session.setAttribute("currentUser", user);		
			
			repository.save(user);
			return user;
		} else {
		return null;
		}
	}
	
	@GetMapping("/api/profile")
	public User profile() {
		User currentUser = (User) this.session.getAttribute("currentUser");	
		return currentUser;
	}
	
	@GetMapping("api/user/review")
	public User review() {
		User currentUser = (User) this.session.getAttribute("currentUser");
		return currentUser;
	}

	
}
