package com.example.demo.services.impl;

import com.example.demo.models.Product;
import com.example.demo.repo.ProductRepository;
import com.example.demo.services.ICRUDProductService;
import com.example.demo.services.IFilterProductService;
import com.example.demo.services.IProduct;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AllProductServiceImpl implements IProduct, ICRUDProductService, IFilterProductService {
	
	@Autowired
    private ProductRepository productRepository;
/*
    public ArrayList<Product> productList = new ArrayList<>(Arrays.asList(new Product("Abols", 0.69, "abols ir sarkans", 5, "Vainovska", 23),
            new Product("Burkans", 0.50, "burkans ir veseligs", 18, "Davis", 24),
            new Product("Bumbiers", 0.89, "bumbiers ir zals", 1, "Clark", 16)));
*/
	//public ArrayList<Product> productList = new ArrayList<>();
	
    @Override
    public ArrayList<Product> selectAllProducts() throws Exception {
        if (productRepository.count()==0) {
            throw new Exception("Product list is empty.");
        }
        return (ArrayList<Product>) productRepository.findAll();
    }
    //The findById() method of JpaRepository (or CrudRepository) in Spring Data JPA returns an Optional<Product>, not a Product directly.
    //Optional class is designed to handle cases where a value might be present or absent.
    //Using Optional helps avoid NullPointerException by forcing the developer
    //to explicitly handle the "absent value" scenario.
    @Override
    public Product selectOneProductById(int id) throws Exception {
        if(productRepository.count()==0){
            throw new Exception("Product list is empty.");
        }

        	if(productRepository.existsById(id)) { //Your code using .get() works because you've already verified the existence of the ID
        		return productRepository.findById(id).get();
        }
            throw new Exception("Product is not on a list");
    }


    @Override
    public void insertNewProduct(Product product) {
    	if(product == null) {
    		throw new IllegalArgumentException("Product cannot be null");
    	}
        productRepository.save(product);
    
    }

	@Override
	public void deleteProductById(int id) throws Exception {
		
		Product deleteProduct = selectOneProductById(id);
		productRepository.delete(deleteProduct);
		
		
	}

	@Override
	public Product create(String title, double price, String description, int quantity, String surname, int age)
			throws Exception {
		if (title == null || title.isEmpty()) {
	        throw new Exception("Title cannot be null or empty");
	    }
	    if (price <= 0) {
	        throw new Exception("Price must be greater than zero");
	    }
	    if (description == null || description.isEmpty()) {
	        throw new Exception("Description cannot be null or empty");
	    }
	    if (surname == null || surname.isEmpty()) {
	        throw new Exception("Surname cannot be null or empty");
	    }
	    if (age <= 0) {
	        throw new Exception("Age must be greater than zero");
	    }
	    
	    // Check if the product already exists in the database
	    Product existingProduct = 
	    		productRepository.findByTitleAndPriceAndDescriptionAndQuantityAndSurnameAndAge
	    		(title, price, description, quantity, surname, age);
	    if(existingProduct!=null) {
	    	existingProduct.setQuantity(existingProduct.getQuantity() + quantity);
	    	productRepository.save(existingProduct);
	    	return productRepository.save(existingProduct);
	    }

	    // Create a new product if not found in the database
	    Product product = new Product(title, price, description, quantity, surname, age);
	    productRepository.save(product);  // Save it in the database
	    
	    return product;  // Return the newly created product
	}

	@Override
	public ArrayList<Product> retrieve() throws Exception {
		if (productRepository.count()==0)
			throw new Exception("Product list is empty");

		return (ArrayList<Product>) productRepository.findAll();
	}

	@Override
	public void update(int id, String title, double price, String description, int quantity, String surname, int age)
			throws Exception {
		Product updateProduct = selectOneProductById(id);
		if(title != null) {
			updateProduct.setTitle(title);
		}
		if(description !=null) {
			updateProduct.setDescription(description);
		}
		if(price >= 0 && price <= 10000) {
			updateProduct.setPrice(price);
		}
		if(quantity >= 0 && quantity <= 100 ) {
			updateProduct.setQuantity(quantity);
		}
		if(surname != null) {
			updateProduct.setSurname(surname);	
		}
		if(age > 1 && age < 100) {
			updateProduct.setAge(age);
		}
		productRepository.save(updateProduct);
	}
	

	
	@Override
	public void delete(int id) throws Exception {
		Product deleteProduct = selectOneProductById(id);
		productRepository.delete(deleteProduct);
		
	}
	
	@Override
	public ArrayList<Product> filterByPriceLessThanThreshold(double threshold) throws Exception {
		
		if(threshold < 0 || threshold > 1000) {
			throw new Exception("The limit of price is wrong");
		}
		
		ArrayList<Product> result = productRepository.findByPriceLessThan(threshold);
		
		return result;
		
	}
	
	/*
	@PostConstruct
	public void syncExistingProductsToDatabase() {
		
		if (!productList.isEmpty()) {
	        productRepository.saveAll(productList);  // Save all in one batch
	    }
		
	    List<Product> copyList = new ArrayList<>(productList);  // Create a copy of the list
	    for (Product product : copyList) {
	        try {
	            // Save each product if it doesn't already exist
	            create(product.getTitle(), product.getPrice(), product.getDescription(),
	                   product.getQuantity(), product.getSurname(), product.getAge());
	        } catch (Exception e) {
	            // Log the exception or handle it accordingly
	            System.err.println("Error saving product: " + e.getMessage());
	        }
	    }
	}*/

	/*
	public Product findByTitle(String title) throws Exception {
		 // Step 1: Check if productList is empty
	    if (productList.isEmpty()) {
	        throw new Exception("Product list is empty.");
	    }

	    // Step 2: Iterate through the productList and check each product's title
	    for (Product product : productList) {
	        if (product.getTitle().equals(title)) { // Check if the title matches
	            return product; // Return the product if a match is found
	        }
	    }

	    // Step 3: If no match is found, throw an exception
	    throw new Exception("Product with title '" + title + "' not found.");
	}	*/
	
}
