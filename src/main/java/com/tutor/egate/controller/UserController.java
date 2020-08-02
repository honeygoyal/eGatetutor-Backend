package com.tutor.egate.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.validation.Valid;

import com.tutor.egate.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tutor.egate.exception.UserExistsException;
import com.tutor.egate.model.CreateUserResponseModel;
import com.tutor.egate.model.JwtRequest;
import com.tutor.egate.model.JwtResponse;
import com.tutor.egate.model.User;
import com.tutor.egate.model.UserCreationRequestModel;
import com.tutor.egate.repo.UserRepository;
import com.tutor.egate.service.UsersService;
import com.tutor.egate.shared.Response;
import com.tutor.egate.util.JwtTokenUtil;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UsersService usersService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@GetMapping("/hello")
	public String test() {
		return "hello";
	}

	@PostMapping("/register")
	public ResponseEntity createUser(@Valid @RequestBody UserCreationRequestModel userdetails)
			throws UserExistsException, AddressException, MessagingException, IOException {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto = modelMapper.map(userdetails, UserDto.class);
		UserDto createdUser = null;
		try {
			createdUser = usersService.createUser(userDto);
		} catch (UserExistsException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Email ID already Registered"));
		}
		CreateUserResponseModel returnValue = modelMapper.map(createdUser, CreateUserResponseModel.class);
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = usersService.loadUserByUsername(authenticationRequest.getUsername());
		User userEntity = userRepository.findByEmail(userDetails.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		JwtResponse jwtResponse = new JwtResponse(token, userEntity);
		return ResponseEntity.status(HttpStatus.OK).body(jwtResponse);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
