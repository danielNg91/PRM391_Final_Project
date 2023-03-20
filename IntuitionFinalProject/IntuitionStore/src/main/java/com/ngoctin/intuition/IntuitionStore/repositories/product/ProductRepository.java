package com.ngoctin.intuition.IntuitionStore.repositories.product;

import com.ngoctin.intuition.IntuitionStore.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>,MyProductRepository {
}
