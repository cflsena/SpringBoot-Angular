package com.example.dev.backend.api.security.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
	public static void main(String[] args) {
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		System.out.println(enconder.encode("julia"));
	}
}
