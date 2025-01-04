package com.example.demo.services;

import com.example.demo.models.Product;

import java.util.ArrayList;

public interface IProduct {

    public abstract ArrayList<Product> selectAllProducts() throws Exception;
    public abstract Product selectOneProductById(int id) throws Exception;
    public abstract void insertNewProduct(Product product);
    public abstract void deleteProductById(int id) throws Exception;


}
