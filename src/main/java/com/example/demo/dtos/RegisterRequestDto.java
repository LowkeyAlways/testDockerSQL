package com.example.demo.dtos;

import com.example.demo.models.User;

import lombok.Data;

@Data
public class RegisterRequestDto {
	private String email;
	private String password;
	private String fullName;
	
}
