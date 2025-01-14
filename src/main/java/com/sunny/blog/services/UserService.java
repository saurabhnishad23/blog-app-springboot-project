package com.sunny.blog.services;

import java.util.List;
import java.util.Optional;

import com.sunny.blog.entities.User;
import com.sunny.blog.payloads.*;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

	UserDto createUser(UserDto userDto);

	UserDto updateUser(UserDto userDto, Integer userId);

	UserDto getUserById(Integer userId);

	List<UserDto> getAllUser();

	void deleteUser(Integer userId);

//	UserDetails loadUserByUsername(String username);

//	Optional<User> findByEmail(String emailId);

}
