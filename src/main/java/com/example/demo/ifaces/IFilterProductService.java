package com.example.demo.ifaces;

import java.util.ArrayList;

import com.example.demo.models.Product;

public interface IFilterProductService {

	//public abstract Product findByTitle(String title) throws Exception;
	
	public abstract ArrayList<Product> filterByPriceLessThanThreshold(double threshold) throws Exception;
	
	//public abstract ArrayList<Product> filterByTitleOrDescription(String text) throws Exception;
	
	//public abstract float calculateProductsTotalValue() throws Exception;

}
