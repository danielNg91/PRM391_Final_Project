package com.ngoctin.intuition.IntuitionStore.repositories.product;

import com.ngoctin.intuition.IntuitionStore.entities.product.Product;
import com.ngoctin.intuition.IntuitionStore.models.product.ProductResponse;

import java.util.List;

public interface MyProductRepository {
    boolean createProduct(Product product,Long cateID);
    Product getProductByName(String name);
    List<Product> searchByLikeName(String name);
    List<Product> getproductsByCategory(int cateID);

}
