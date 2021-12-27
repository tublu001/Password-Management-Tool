package com.epam.controller;

import com.epam.dto.AuthenticationRequest;
import com.epam.dto.AuthenticationResponse;
import com.epam.security.AppAuthUserDetailsService;
import com.epam.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthApiController
{

	@Autowired
	private AppAuthUserDetailsService service;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("authenticate")
	public ResponseEntity<?> doRequestAuthenticate(@RequestBody AuthenticationRequest request) {

		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		}catch(BadCredentialsException be) {
			throw new BadCredentialsException("Invalid username and password");
		}
		
		final UserDetails userDetails = service.loadUserByUsername(request.getUsername());
		
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
 	}

}
