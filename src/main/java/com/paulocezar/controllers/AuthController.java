package com.paulocezar.controllers;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paulocezar.data.model.User;
import com.paulocezar.repository.UserRepository;
import com.paulocezar.security.AccountCredentialsVO;
import com.paulocezar.security.jwt.JwtTokenProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "AuthenticationEndpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider tokenProvider;

	@Autowired
	UserRepository repository;

	@ApiOperation(value = "Autenticate a user by credentials")
	@PostMapping(value = "/signin", produces = { "application/json", "application/xml",
			"application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
	@SuppressWarnings("rawtypes")
	public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {
		try {
			String username = data.getUsername();
			String password = data.getPassword();

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			User user = repository.findByUsername(username);
			String token = "";

			if (user != null) {
				token = tokenProvider.createToken(username, user.getRoles());
			} else {
				throw new UsernameNotFoundException("Username " + username + "Not found");
			}
			Map<Object, Object> model = new HashMap<>();
			model.put("username", username);
			model.put("token", token);
			return ok(model);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid username/password supplied");
		}
	}

}
