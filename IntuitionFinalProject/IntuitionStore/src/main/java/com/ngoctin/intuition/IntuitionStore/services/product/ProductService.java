package com.ngoctin.intuition.IntuitionStore.services.product;

import com.ngoctin.intuition.IntuitionStore.entities.product.Product;
import com.ngoctin.intuition.IntuitionStore.models.product.ProductResponse;

import java.util.List;

public interface ProductService {

    boolean createProduct(Product product, Long cateID);
    List<Product> getAllProducts();
    ProductResponse getProductByName(String name);
    List<Product> searchByLikeName(String name);
    List<Product> getProductsByCateID(int cateID);
    ProductResponse getProductByID(int productID);


}
