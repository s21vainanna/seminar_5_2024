package com.example.demo.ifaces;

import com.example.demo.models.Product;

import java.util.ArrayList;

public interface IProduct {

    public abstract ArrayList<Product> selectAllProducts() throws Exception;
    public abstract Product selectOneProductById(int id) throws Exception;
    public abstract void insertNewProductByObject(Product product);
    public abstract void insertNewProductByParameters(Product product);
    public abstract Product deleteProductById(int id) throws Exception;


}
