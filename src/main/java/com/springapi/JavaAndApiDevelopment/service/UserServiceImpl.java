package com.springapi.JavaAndApiDevelopment.service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springapi.JavaAndApiDevelopment.dto.AddressDto;
import com.springapi.JavaAndApiDevelopment.dto.UserRequest;
import com.springapi.JavaAndApiDevelopment.dto.UserResponse;
import com.springapi.JavaAndApiDevelopment.model.Address;
import com.springapi.JavaAndApiDevelopment.model.User;
import com.springapi.JavaAndApiDevelopment.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
public class UserServiceImpl {

	private final UserRepository userRepository ;
	
	
	 public UserServiceImpl(UserRepository userRepository) { 
		 this.userRepository = userRepository; 
	 }
	 
	//private List<Users> userList = new ArrayList<Users>();
    //@Autowired
	// UserRepository userRepository;
	
	public List<UserResponse> fetchAllUsers(){
//	    return userRepository.findAll();
		return userRepository.findAll().stream()
				.map(this::mapToUserResponse)
				.collect(Collectors.toList());
	}
	
	public void addUser(UserRequest userRequest) {
		User user = new User();
		updateUserFromRequest(user , userRequest);
		userRepository.save(user);
		
	}

	private void updateUserFromRequest(User user, UserRequest userRequest) {
		user.setFirstName(userRequest.getFirstName());
		user.setLastName(userRequest.getLastName());
		user.setEmail(userRequest.getEmail());
		user.setPhone(userRequest.getPhone());
		
		if(userRequest.getAddress()!=null) {
			Address address = new Address();
			address.setStreet(userRequest.getAddress().getStreet());
			address.setState(userRequest.getAddress().getState());
			address.setCity(userRequest.getAddress().getCity());
			address.setCountry(userRequest.getAddress().getCountry());
			address.setZipcode(userRequest.getAddress().getZipcode());
			
			user.setAddress(address);
		}
	}

	public Optional<UserResponse> fetchUser(Long id) {
		/*
		 * for(Users user: userList) { if(user.getId().equals(id)) { return user; } }
		 * return null;
		 */
//		return userList.stream()
//				.filter(user->user.getId().equals(id))
//				.findFirst();
		return userRepository.findById(id)
				.map(this::mapToUserResponse);
		
	}

	public boolean updateUser(Long id , UserRequest updatedUserRequest) {
//		return userList.stream()
//				.filter(user->user.getId().equals(id))
//				.findFirst()
//				.map(existingUser->{
//					existingUser.setFirstName(updateUser.getFirstName());
//					existingUser.setLastName(updateUser.getLastName());
//					return true;
//				}).orElse(false);
		
		/*
		 * return userRepository.findById(id) .map(existingUser ->{
		 * existingUser.setFirstName(updateUser.getFirstName());
		 * existingUser.setLastName(updateUser.getLastName());
		 * userRepository.save(existingUser);
		 * 
		 * return true; }).orElse(false);
		 */
		return userRepository.findById(id)
				.map(existingUser ->{
					updateUserFromRequest(existingUser , updatedUserRequest);
					userRepository.save(existingUser);
					return true;
				}).orElse(false);
				
	}
	
	private UserResponse mapToUserResponse(User user) {
		UserResponse userresponse = new UserResponse();
		userresponse.setId(String.valueOf(user.getId()));
		userresponse.setFirstName(user.getFirstName());
		userresponse.setLastName(user.getLastName());
		userresponse.setEmail(user.getEmail());
		userresponse.setPhone(user.getPhone());
		userresponse.setRole(user.getRole());
		
		if(user.getAddress()!=null) {
			AddressDto addressDto = new AddressDto();
			addressDto.setStreet(user.getAddress().getStreet());
			addressDto.setCity(user.getAddress().getCity());
			addressDto.setState(user.getAddress().getCity());
			addressDto.setCountry(user.getAddress().getCountry());
			addressDto.setZipcode(user.getAddress().getZipcode());
			
			userresponse.setAddress(addressDto);
		}
		return userresponse;
	}

	
	

	
}
