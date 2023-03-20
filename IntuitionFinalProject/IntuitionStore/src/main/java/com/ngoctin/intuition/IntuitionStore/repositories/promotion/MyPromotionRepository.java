package com.ngoctin.intuition.IntuitionStore.repositories.promotion;

import com.ngoctin.intuition.IntuitionStore.entities.promotion.Promotion;

import java.util.List;

public interface MyPromotionRepository {

    List<Promotion> getPromotionsByUserID(int id);
    boolean setUsedForPromotionByUserID(int promotionId, int userID);

}
