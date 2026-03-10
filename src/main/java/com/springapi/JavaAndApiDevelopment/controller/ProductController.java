package com.springapi.JavaAndApiDevelopment.controller;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springapi.JavaAndApiDevelopment.dto.ProductRequest;
import com.springapi.JavaAndApiDevelopment.dto.ProductResponse;
import com.springapi.JavaAndApiDevelopment.service.ProductServiceImpl;


@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	private final ProductServiceImpl productServiceImpl;
	
	
	public ProductController(ProductServiceImpl productServiceImpl) {
		this.productServiceImpl = productServiceImpl;
	}

	@PostMapping
	public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest){
		return new ResponseEntity<ProductResponse>(productServiceImpl.createProduct(productRequest), 
				                HttpStatus.CREATED);
	}
	@GetMapping
	public ResponseEntity<List<ProductResponse>> getAllUsers(){
		//return ResponseEntity.ok(userServiceImpl.fetchAllUsers());
		return new ResponseEntity<>(productServiceImpl.fetchAllUsers(),HttpStatus.OK);
		//return userServiceImpl.fetchAllUsers();
	}

}
