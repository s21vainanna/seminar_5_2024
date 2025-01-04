package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.services.ICRUDProductService;
import com.example.demo.services.impl.AllProductServiceImpl;

import jakarta.validation.Valid;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/product")
public class ProductController {

    //Dependency injection
    @Autowired
    private AllProductServiceImpl productService;
    

    @GetMapping("/selectAll") //https://localhost:8080/product/selectAll       Fetches data(from productList) using a service, Adds data to the Model object, the view for example Thymeleaf accesses the data stored in the model
    public String selectAll(Model model) throws Exception {
        model.addAttribute("productList", productService.selectAllProducts());
        return "product-all-show-page";
    }

    @GetMapping("/select/{id}")  //https://localhost:8080/product/select/id
    public String selectOneProduct(@PathVariable("id") int id, Model model) throws Exception {
        model.addAttribute("productList", productService.selectOneProductById(id));
        return "product-one-show-page";
    }

    /*
    @PostMapping("/insertProduct")
    public String insertProduct(Model model){
        model.addAttribute("productList", productService.insertNewProductByObject(product));
        return "insert-product-page";
    }*/
    /*
    @GetMapping("/insertProductByObject")
    public String showInsertProductForm(Model model) {
        Product product = new Product(); // Create an empty product object
        //model.addAttribute("product", product);
        model.addAttribute("product", product);
        return "insert-product-page-object";
    }
    
    @PostMapping("/insertProductByObject")
    public String insertProduct(Product product) throws Exception {
        productService.insertNewProductByObject(product); // Call the service to save the product
        return "redirect:/product/selectAll"; // Redirect to the product list page after insertion
    }*/
    
    
    
 // GET Mapping: Show the form to insert a new product
    @GetMapping("/insertProduct")
    public String showInsertProductForm1(Model model) {
        // Adding a new, empty Product object to bind form fields
        model.addAttribute("product", new Product());
        return "insert-product-page";  
    }
    
    @PostMapping("/insertProduct")
    public String insertProduct(
            @ModelAttribute @Valid Product product,   // Bind the incoming form data to Product and validate it
            BindingResult result) {                   // To capture validation errors

        if (result.hasErrors()) {
            // If validation errors exist, return to the form with the validation errors
            return "product-form";  // Replace with your form view name
        }

        // If no validation errors, insert the product
        productService.insertNewProduct(product);

        // Redirect to the product list page after insertion
        return "redirect:/product/selectAll";
    }

/*
    // POST Mapping: Handle the form submission to add the product
    @PostMapping("/insertProductByParams")
    public String insertProduct(
            @RequestParam("title") String title,
            @RequestParam("price") double price,
            @RequestParam("description") String description,
            @RequestParam("quantity") int quantity,
            @RequestParam("surname") String surname,
            @RequestParam("age") int age) {

        // Call the service method to insert the new product
        productService.insertNewProductByParameters(title, price, description, quantity, surname, age);
        
        // Redirect to the product list page after insertion
        return "redirect:/product/selectAll";
    }   */
    
    /*
    @PostMapping("/insertProductByParams")
    public String insertProduct1(@ModelAttribute Product product) {
        // The product object will be automatically populated from the form fields
        productService.insertNewProductByObject(product);
        return "redirect:/product/selectAll";
    }*/
    
    @GetMapping("/delete/{id}")  //https://localhost:8080/product/delete/id
    public String deleteById(@PathVariable("id") int id, Model model) throws Exception {
    	productService.deleteProductById(id);
        model.addAttribute(productService.retrieve());
        return "product-all-show-page";
    }
    
    @GetMapping("/createProduct")
    public String showCreateProductForm2(Model model) {
        model.addAttribute("product", new Product());
        return "create-product-page";  
    }

    @PostMapping("/createdProduct")
    public String createProduct(
            @RequestParam("title") String title,
            @RequestParam("price") double price,
            @RequestParam("description") String description,
            @RequestParam("quantity") int quantity,
            @RequestParam("surname") String surname,
            @RequestParam("age") int age) throws Exception {

        // Call the service method to insert the new product
        productService.create(title, price, description, quantity, surname, age);
        // Redirect to the product list page after insertion
        return "redirect:/product/selectAll";
    }   
    
    @GetMapping("/update/{id}")
    public String showUpdateProductForm(@PathVariable("id") int id, Model model) throws Exception {
    	Product updatedProduct = productService.selectOneProductById(id);
    	model.addAttribute(updatedProduct);
    	return "update-product-page";
    }

    @PostMapping("/update")
    public String getUpdatedProductById(@Valid Product product, 
			BindingResult result, @RequestParam("id") int id, Model model) throws Exception {
    	
    	if (result.hasErrors()) {
            // If validation errors exist, return to the form with the validation errors
            return "update-product-page";  // Replace with your form view name
        }
    	
    	productService.update(id,product.getTitle(), product.getPrice(), product.getDescription(), 
    			product.getQuantity(), product.getSurname(), product.getAge());
    	return "redirect:/product/selectAll";
    }
    
    @GetMapping("/price/{threshold}")
    public String findAllProductsLessThanThreshold(@PathVariable("threshold") double threshold,
    		Model model) throws Exception {
		
    	ArrayList<Product> allProducts = productService.filterByPriceLessThanThreshold(threshold);
		model.addAttribute(allProducts);
		return "product-all-show-page";
    	
    }
    
}
