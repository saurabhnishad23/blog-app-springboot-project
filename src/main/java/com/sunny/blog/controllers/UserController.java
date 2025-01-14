package com.sunny.blog.controllers;

import com.sunny.blog.entities.AuthRequest;
import com.sunny.blog.entities.User;
import com.sunny.blog.payloads.ApiResponse;
import com.sunny.blog.services.impl.JwtService;
import com.sunny.blog.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import com.sunny.blog.payloads.UserDto;
import com.sunny.blog.services.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtService jwtService;
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	@PostMapping("/addUser")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createUserToDto = userService.createUser(userDto);
		return new ResponseEntity<>(createUserToDto, HttpStatus.CREATED);
	}

	@PutMapping("/{userId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId){
		UserDto updatedUser = userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUser);
	}
	@DeleteMapping("/{userId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user Deleted Successfully", true), HttpStatus.OK);
	}

	@GetMapping("/admin/adminProfile")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(userService.getAllUser());
	}

	@GetMapping("/user/{userId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<UserDto> getUser(@PathVariable Integer userId){
		return ResponseEntity.ok(userService.getUserById(userId));
	}

	@PostMapping("/generateToken")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
		);
		System.out.println("Something wrong");

		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getUsername());
		} else {
			throw new UsernameNotFoundException("Invalid user request!");
		}
	}
	
}
