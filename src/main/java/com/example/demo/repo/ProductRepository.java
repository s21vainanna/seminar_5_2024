package com.example.demo.repo;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	public abstract Optional findByTitle(String title) throws Exception;
}
