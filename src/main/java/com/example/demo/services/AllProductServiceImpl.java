package com.example.demo.services;

import com.example.demo.ifaces.ICRUDProductService;
import com.example.demo.ifaces.IFilterProductService;
import com.example.demo.ifaces.IProduct;
import com.example.demo.models.Product;
import com.example.demo.repo.ProductRepository;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AllProductServiceImpl implements IProduct, ICRUDProductService {
	
	@Autowired
    private ProductRepository productRepository;

    public ArrayList<Product> productList = new ArrayList<>(Arrays.asList(new Product("Abols", 0.69, "abols ir sarkans", 5, "vainovska", 23),
            new Product("Burkans", 0.50, "burkans ir veseligs", 18, "Davis", 24),
            new Product("Bumbiers", 0.89, "bumbiers ir zals", 1, "Clark", 16)));

    @Override
    public ArrayList<Product> selectAllProducts() throws Exception {
        if (productList.isEmpty()) {
            throw new Exception("Product list is empty.");
        }
        return productList;
    }

    @Override
    public Product selectOneProductById(int id) throws Exception {
        if(productList.isEmpty()){
            throw new Exception("Product list is empty.");
        }

        if (id >= 0 && id < productList.size()) {
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).getId() == id) {
                    return productList.get(i);
                }
            }
        }
            throw new Exception("Product is not on a list");
    }

    @Override
    public void insertNewProductByObject(Product newProduct) {
        if (newProduct == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        // newProduct = new Product(); // This overwrites the passed object!
        productList.add(newProduct);

    }

    @Override
    public void insertNewProductByParameters(String title, double price, String description, int quantity, String surname, int age) {
    	Product newProduct = new Product(title, price, description, quantity, surname, age);
        productList.add(newProduct);
    
    }

	@Override
	public Product deleteProductById(int id) throws Exception {
		if(productList.isEmpty()){
            throw new Exception("Product list is empty.");
        }
		
		if (id >= 0 && id < productList.size()) {
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).getId() == id) {
                    return productList.remove(i);
                }
            }
        }
            throw new Exception("Product is not on a list");
		
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
	    Optional<Product> existingProduct = productRepository.findByTitle(title);
	    if (existingProduct.isPresent()) {
	        System.out.println("Product with this title already exists in the database: " + title);
	        return existingProduct.get();  // Return the existing product if found
	    }

	    // Create a new product if not found in the database
	    Product product = new Product(title, price, description, quantity, surname, age);
	    productRepository.save(product);  // Save it in the database
	    productList.add(product);         // Optionally, add it to the in-memory list

	    return product;  // Return the newly created product
	}

	@Override
	public ArrayList<Product> retrieve() throws Exception {
		if (productList.isEmpty())
			throw new Exception("Product list is empty");

		return productList;
	}

	@Override
	public void update(int id, String title, double price, String description, int quantity, String surname, int age)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@PostConstruct
	public void syncExistingProductsToDatabase() {
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
	}

	
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
	}	
	
}
