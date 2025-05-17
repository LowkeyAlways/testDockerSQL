package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User register(User user) {
		if (userRepository.findByEmail(user.getEmail()) != null) {
			throw new RuntimeException("Email déjà utilisé");
		}
		return userRepository.save(user);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public boolean findByEmail(String email) {
		return userRepository.findByEmail(email) != null;
	}

}
