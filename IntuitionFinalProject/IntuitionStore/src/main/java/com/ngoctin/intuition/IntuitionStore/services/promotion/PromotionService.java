package com.ngoctin.intuition.IntuitionStore.services.promotion;

import com.ngoctin.intuition.IntuitionStore.entities.promotion.Promotion;

import java.util.List;

public interface PromotionService {

   List<Promotion> getPromotionsByUserID(int id);
   boolean setUsedForPromotionByUserID(int promotionId, int userId);

}
