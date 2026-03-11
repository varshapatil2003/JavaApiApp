package com.springapi.JavaAndApiDevelopment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.springapi.JavaAndApiDevelopment.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
