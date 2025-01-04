package com.example.demo.repo;



import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Product;
//repozitorijs automātiski sasaistīts ar datubāzi
@Repository                //strādā tikai uz produkt klasi, tāpēc tie mainīgie ir tie kurus var atlasīt
public interface ProductRepository extends CrudRepository<Product, Integer>{

	//SELECT * FROM PRODUCT_TABLE WHERE TITLE = title, PRICE = price, DESCRIPTION = description, QUANTITY = quantity,
	//SURNAME = surname, AGE = age
	//funkcijas atbilstošo ķermeni uzprogrammēs Data JPA mūsu vietā
	Product findByTitleAndPriceAndDescriptionAndQuantityAndSurnameAndAge(String title, double price, String description,
			int quantity, String surname, int age);

	ArrayList<Product> findByPriceLessThan(double threshold);
	
	
}
