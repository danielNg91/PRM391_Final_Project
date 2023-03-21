package com.ngoctin.intuition.IntuitionStore.repositories.promotion;

import com.ngoctin.intuition.IntuitionStore.entities.promotion.Promotion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class MyPromotionRepositoryImpl implements MyPromotionRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Promotion> getPromotionsByUserID(int id) {
        try {

            String sql = " SELECT p.* " +
                    "FROM users u " +
                    "JOIN users_promotions up ON u.id = up.user_id " +
                    "JOIN promotions p ON up.promotion_id = p.id " +
                    "AND up.isUsed = 'true' " +
                    "WHERE u.id = ? ";
            Query query = entityManager.createNativeQuery(sql,Promotion.class);
            query.setParameter(1,id);
            List<Promotion> promotions = (List<Promotion>)query.getResultList();
            if(promotions != null && promotions.size() > 0){
                return promotions;
            }
            return null;
        }catch (Exception exception){
            return null;
        }
    }

    @Override
    @Transactional
    public boolean setUsedForPromotionByUserID(int promotionId, int userID) {

        try {
            String sql = "UPDATE users_promotions " +
                    "SET isUsed = 'true' " +
                    "WHERE user_id = ? and promotion_id = ?";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1,userID);
            query.setParameter(2,promotionId);
            if(query.executeUpdate() > 0){
                return true;
            }
            return false;
        }catch (Exception exception){
            return false;
        }
    }
}
