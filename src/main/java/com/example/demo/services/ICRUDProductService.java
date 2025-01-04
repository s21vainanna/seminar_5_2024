package com.example.demo.services;

import java.util.ArrayList;

import com.example.demo.models.Product;

public interface ICRUDProductService {
	
	//CRUD - create - retrieve - update - delete
	public abstract Product create(String title, double price, 
			String description, int quantity, String surname, int age) throws Exception;
	
	public abstract ArrayList<Product> retrieve() throws Exception;
	
	public abstract void update(int id, String title, double price, 
			String description, int quantity, String surname, int age) throws Exception;
	
	public abstract void delete(int id) throws Exception;

}
