package com.springapi.JavaAndApiDevelopment.service;



import org.springframework.stereotype.Service;

import com.springapi.JavaAndApiDevelopment.dto.ProductRequest;
import com.springapi.JavaAndApiDevelopment.dto.ProductResponse;
import com.springapi.JavaAndApiDevelopment.model.Product;
import com.springapi.JavaAndApiDevelopment.repository.ProductRepository;


@Service
public class ProductServiceImpl {

	private final ProductRepository productRepository;
	

	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	public  ProductResponse createProduct(ProductRequest productRequest) {
		Product product = new Product();
		updateProductFromRequest(product, productRequest);
		Product savedProduct = productRepository.save(product) ;
	
		return mapToProductResponse(savedProduct);
	}
	

	private ProductResponse mapToProductResponse(Product savedProduct) {
        ProductResponse response = new ProductResponse();
        response.setId(savedProduct.getId());
        response.setName(savedProduct.getName());
        response.setActive(savedProduct.getActive());
        response.setCategory(savedProduct.getCategory());
        response.setPrice(savedProduct.getPrice());
        response.setImageUrl(savedProduct.getImageUrl());
        response.setStockQuantity(savedProduct.getStockQuantity());
        response.setDescription(savedProduct.getDescription());
		return response;
	}

	private void updateProductFromRequest(Product product, ProductRequest productRequest) {
		product.setName(productRequest.getName());
		product.setCategory(productRequest.getCategory());
		product.setPrice(productRequest.getPrice());
		product.setImageUrl(productRequest.getImageUrl());
		product.setStockQuantity(productRequest.getStockQuantity());
		product.setDescription(productRequest.getDescription());
	}

	public List<ProductResponse> fetchAllUsers() {
//		    return userRepository.findAll();
			return productRepository.findAll().stream()
					.map(this::mapToProductResponse)
					.collect(Collectors.toList());
		}
	
}
