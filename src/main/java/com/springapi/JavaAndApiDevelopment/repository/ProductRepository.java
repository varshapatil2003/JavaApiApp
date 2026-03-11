package com.springapi.JavaAndApiDevelopment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.stereotype.Repository;

import com.springapi.JavaAndApiDevelopment.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	

}
