package com.ngoctin.intuition.IntuitionStore.services.product;

import com.ngoctin.intuition.IntuitionStore.entities.product.Product;
import com.ngoctin.intuition.IntuitionStore.models.product.ProductResponse;
import com.ngoctin.intuition.IntuitionStore.repositories.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public boolean createProduct(Product product, Long cateID) {
        try {
            if (productRepository.createProduct(product, cateID)) {
                return true;
            }
            return false;
        } catch (Exception exception) {
            log.error("Exception : " + exception.getMessage());
            return false;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception exception) {
            log.error("Exception : " + exception.getMessage());
            return null;
        }
    }

    @Override
    public ProductResponse getProductByName(String name) {
        try {
            Product product = productRepository.getProductByName(name);
            int id = Integer.parseInt(product.getId()+"");
            ProductResponse productResponse =
                    new ProductResponse(id, product.getName(), product.getPrice(),
                            product.getQuantity(), product.getDescription(), product.getUrl());
            return productResponse;
        } catch (Exception exception) {
            log.error("Exception : " + exception.getMessage());
            return null;
        }
    }

    @Override
    public List<Product> searchByLikeName(String name) {
        try {
            return productRepository.searchByLikeName(name);
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    public List<Product> getProductsByCateID(int cateID) {
        try {
            return productRepository.getproductsByCategory(cateID);
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    public ProductResponse getProductByID(int productID) {
        try {
            Product product = productRepository.findById(Long.parseLong(productID+"")).get();
            if(product != null){
                ProductResponse productResponse = new ProductResponse(
                        Integer.parseInt(product.getId().toString()),
                        product.getName(),
                        product.getPrice(),
                        product.getQuantity(),
                        product.getDescription(),
                        product.getUrl());
                return productResponse;
            }
            return null;
        } catch (Exception exception) {
            return null;
        }
    }
}
