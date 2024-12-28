package com.example.demo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
}
