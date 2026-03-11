package com.springapi.JavaAndApiDevelopment.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springapi.JavaAndApiDevelopment.dto.UserRequest;
import com.springapi.JavaAndApiDevelopment.dto.UserResponse;
import com.springapi.JavaAndApiDevelopment.service.UserServiceImpl;


@RestController
public class UserController {

    //private final Springudemiyappl1Application springudemiyappl1Application;

	//first way
//	@Autowired
//	private UserServiceImpl userServiceImpl;
	
	//Second way
//	public UserController(List<Users> userList, UserServiceImpl userServiceImpl) {
//		super();
//		this.userList = userList;
//		this.userServiceImpl = userServiceImpl;
//	}
	
    
	private final UserServiceImpl userServiceImpl;

    UserController( UserServiceImpl userServiceImpl) {
        //this.springudemiyappl1Application = springudemiyappl1Application;
		this.userServiceImpl = userServiceImpl;
    }

	@GetMapping("/api/users")
	public ResponseEntity<List<UserResponse>> getAllUsers(){
		//return ResponseEntity.ok(userServiceImpl.fetchAllUsers());
		return new ResponseEntity<>(userServiceImpl.fetchAllUsers(),HttpStatus.OK);
		//return userServiceImpl.fetchAllUsers();
	}
	
	@GetMapping("/api/users/{id}")
	public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
		//return userServiceImp.fetchUser(id);
		return userServiceImpl.fetchUser(id)
				.map(ResponseEntity::ok)
				.orElseGet(()-> ResponseEntity.notFound().build());
	}
	
	@PostMapping("/api/users")
	public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest){
		userServiceImpl.addUser(userRequest);
		return ResponseEntity.ok("User added successfully");
	}
	
	@PutMapping("/api/users/{id}")
	public ResponseEntity<String> updateUser(@PathVariable Long id,
			                  @RequestBody UserRequest updateUserRequest){
	
		boolean updated = userServiceImpl.updateUser(id, updateUserRequest);
		if(updated)
			return ResponseEntity.ok("User Added Successfully");
		return ResponseEntity.notFound().build();
		
	}
	
	
}
