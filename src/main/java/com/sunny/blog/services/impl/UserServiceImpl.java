package com.sunny.blog.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.sunny.blog.entities.User;
import com.sunny.blog.exception.ResourceNotFoundException;
import com.sunny.blog.payloads.*;
import com.sunny.blog.repository.UserRepo;
import com.sunny.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private User user;

	@Autowired
	private PasswordEncoder passwordEncoder;
//	@Override
//	public UserDto createUser(UserDto userDto) {
//
//		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
//		User user = dtoToUser(userDto);
//
//		User savedUser = userRepo.save(user);
//		return userToDto(savedUser);
//	}

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = new User();
		user.setName(userDto.getName());
		user.setPassword(passwordEncoder.encode(userDto.getPassword())); // Encode password
		user.setEmailId(userDto.getEmailId());
		user.setRoles(userDto.getRoles());
		user.setAbout(userDto.getAbout());

		User savedUser = userRepo.save(user);
		return modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		user.setName(userDto.getName());
		user.setEmailId(userDto.getEmailId());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setAbout(userDto.getAbout());
		User updateUser = userRepo.save(user);
		UserDto userDto1 = userToDto(updateUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		return userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users = userRepo.findAll();

		List<UserDto> userDtos = users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		userRepo.delete(user);
	}

	public User dtoToUser(UserDto userDto) {

		User user = modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmailId(userDto.getEmailId());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;
	}

	public UserDto userToDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmailId(user.getEmailId());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}

	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		Optional<User> userDetail = userRepo.findByEmailId(emailId); // Assuming 'email' is used as username

//		Optional<User> userDetail = Optional.ofNullable(userRepo.findByEmailId(emailId)).orElseThrow(() -> new ResourceNotFoundException("email", "emailId", 0)); ;
		// Converting UserInfo to UserDetails
		return userDetail.map(UserInfoDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + emailId));

	}


}
