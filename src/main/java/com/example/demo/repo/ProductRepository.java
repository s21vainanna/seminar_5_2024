package com.example.demo.repo;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Product;
//repozitorijs automātiski sasaistīts ar datubāzi
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{
	
	public abstract Optional findByTitle(String title) throws Exception;
}
