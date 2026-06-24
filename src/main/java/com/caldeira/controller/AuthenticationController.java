package com.caldeira.controller;

import com.caldeira.config.security.BCryptService;
import com.caldeira.controller.dto.*;
import com.caldeira.model.User;
import com.caldeira.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caldeira.config.security.TokenService;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	
	@Autowired
    UserRepository userRepository;

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDTO> authenticate(@RequestBody LoginDTO loginDTO) {
		UsernamePasswordAuthenticationToken loginData = loginDTO.convert();
		Authentication authentication = authManager.authenticate(loginData);
		String token = tokenService.generateToken(authentication);
		return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody UserDTO userDTO) {
		System.out.println(userDTO.getEmail());
		if(userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
			return ResponseEntity.badRequest().body("Email already in use");
		}

		User user = new User(userDTO.getEmail(), BCryptService.criptografarSenha(userDTO.getPassword()));
		userRepository.save(user);

		return ResponseEntity.ok(new UserWithoutPasswordDTO(user.getEmail()));
	}
}
