package com.example.crud_jee.DAO;

import com.example.crud_jee.models.Product;

import java.util.List;

public interface IProductDAO {
    public boolean addProduct(Product product);

    public boolean removeProduct(Product product);

    public List<Product> getProducts();

    public default void updateProduct(Product product) {
        System.out.println("TODO : IMPLEMENT THE UPDATE FUNCTIONALITY");
    }
}
