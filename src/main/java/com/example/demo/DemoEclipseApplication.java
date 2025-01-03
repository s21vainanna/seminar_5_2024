package com.example.demo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.models.Product;
import com.example.demo.repo.ProductRepository;

@SpringBootApplication
public class DemoEclipseApplication {

	    @Autowired
	    private DataSource dataSource;

	    public static void main(String[] args) {
	        SpringApplication.run(DemoEclipseApplication.class, args);
	    }

	    @Bean
	    public CommandLineRunner demo() {
	        return (args) -> {
	            try (Connection connection = dataSource.getConnection()) {
	                if (connection != null) {
	                    System.out.println("Connected to the database via Spring Boot!");
	                }
	            } catch (SQLException e) {
	                System.err.println("Connection failed!");
	                e.printStackTrace();
	            }
	        };
	    }
	    
	    @Bean //sī funkcija stratēsies automātiski, pēc programmas palaišanas
		public CommandLineRunner testModelLayer(ProductRepository productRepository) {
			return new CommandLineRunner() {
				
				@Override
				public void run(String... args) throws Exception {
					Product p1 = new Product("Abols", 0.69, "abols ir sarkans", 5, "Vainovska", 23);
					Product p2 = new Product("Burkans", 0.50, "burkans ir veseligs", 18, "Davis", 24);
					Product p3 = new Product("Bumbiers", 0.89, "bumbiers ir zals", 1, "Clark", 16);
					productRepository.save(p1);
					productRepository.save(p2);
					productRepository.save(p3);
					//System.out.println("How Many:" + productRepo.count());//3
					//System.out.println("Get:" + productRepo.findById(2).get());//Arbuzam
					//Product updatedPr = productRepo.findById(2).get();//Arbuzs
					//updatedPr.setQuantity(20);
					//productRepo.save(updatedPr);//šeit jau būs 20 Arbuzam
					
				}
			};
		}

}
