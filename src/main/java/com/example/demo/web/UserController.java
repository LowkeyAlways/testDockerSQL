package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.LoginRequestDto;
import com.example.demo.dtos.RegisterRequestDto;
import com.example.demo.models.User;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/test")
	public String testUserApi() {
		return "User Controller OK";
	}

	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		if(users.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(users);
	}

	@PostMapping("/add")
	public ResponseEntity<User> register(@RequestBody User user) {
		if(userService.findByEmail(user.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		User registeredUser = userService.register(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
	}
	
}
