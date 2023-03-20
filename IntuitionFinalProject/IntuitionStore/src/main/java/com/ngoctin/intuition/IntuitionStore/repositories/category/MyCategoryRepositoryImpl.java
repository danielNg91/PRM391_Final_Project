package com.ngoctin.intuition.IntuitionStore.repositories.category;

import com.ngoctin.intuition.IntuitionStore.ApplicationUtils;
import com.ngoctin.intuition.IntuitionStore.entities.category.Category;
import com.ngoctin.intuition.IntuitionStore.models.category.CategoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MyCategoryRepositoryImpl implements MyCategoryRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public boolean createCategory(String categoryName) {
        try {
            String sql = "INSERT INTO categories " +
                    "VALUES(?,?,?,?)";
            Query query = entityManager.createNativeQuery(sql,Category.class);
            query.setParameter(1,categoryName);
            query.setParameter(2, ApplicationUtils.getCurrentTime());
            query.setParameter(3,null);
            query.setParameter(4,false);
            if(query.executeUpdate() > 0){
                return true;
            }
            return false;
        }catch (Exception exception){
            log.error("Exception : " + exception.getMessage());
            return false;
        }
    }

    @Override
    public List<Category> searchByLikeName(String name) {
        try {
            String sql = " SELECT id, name, created_date, last_modified, is_deleted" +
                    " FROM categories " +
                    " WHERE name like ?";
            Query query = entityManager.createNativeQuery(sql,Category.class);
            query.setParameter(1,"%" + name + "%");
            List<Category> categoryList = query.getResultList();
            if (categoryList != null) {
                for (Category category:
                     categoryList) {
                    log.info("Cate :" + category.toString());
                }
                return categoryList;
            } else {
                return null;
            }
        } catch (Exception exception) {
            return null;
        }
    }
}
