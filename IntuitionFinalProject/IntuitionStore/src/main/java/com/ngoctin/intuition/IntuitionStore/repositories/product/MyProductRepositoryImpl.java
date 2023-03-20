package com.ngoctin.intuition.IntuitionStore.repositories.product;

import com.ngoctin.intuition.IntuitionStore.ApplicationUtils;
import com.ngoctin.intuition.IntuitionStore.entities.product.Product;
import com.ngoctin.intuition.IntuitionStore.models.product.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MyProductRepositoryImpl implements  MyProductRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public boolean createProduct(Product product, Long cateID) {
        try {
            String sql = "INSERT INTO products " +
                    "VALUES(?,?,?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(sql, Product.class);
            query.setParameter(1, product.getName());
            query.setParameter(2, product.getPrice());
            query.setParameter(3, product.getQuantity());
            query.setParameter(4, ApplicationUtils.getCurrentTime());
            query.setParameter(5, null);
            query.setParameter(6, null);
            query.setParameter(7, cateID);
            query.setParameter(8, false);
            if (query.executeUpdate() > 0) {
                return true;
            }
            return false;
        } catch (Exception exception) {
            log.error("Exception" + exception.getMessage());
            return false;
        }
    }

    @Override
    public Product getProductByName(String name) {
        try {
            String sql = "SELECT * FROM products " +
                    "WHERE name = ?";
            Query query = entityManager.createNativeQuery(sql, Product.class);
            query.setParameter(1, name);
            Product product = (Product) query.getResultList().stream().findFirst().get();
            return product;
        } catch (Exception exception) {
            log.error("Exception" + exception.getMessage());
            return null;
        }
    }

    @Override
    public List<Product> searchByLikeName(String name) {
        try {
            String sql = " SELECT * " +
                    " FROM products " +
                    " WHERE name LIKE ?";
            Query query = entityManager.createNativeQuery(sql, Product.class);
            query.setParameter(1, "%" + name + "%");
            List<Product> products = query.getResultList();
            if (products != null) {
                return products;
            } else {
                return null;
            }
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    public List<Product> getproductsByCategory(int cateID) {
        try {
            String sql = "SELECT * FROM products p where p.cate_id = ?";
            Query query = entityManager.createNativeQuery(sql, Product.class);
            query.setParameter(1,cateID);
            List<Product> products = query.getResultList();
            if (products != null) {
                return products;
            } else {
                return null;
            }
        } catch (Exception exception) {
            return null;
        }
    }

}
