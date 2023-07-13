package com.example.web_demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.web_demo.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	List<Product> findByProductName(String productName);
}
