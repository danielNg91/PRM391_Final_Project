package com.ngoctin.intuition.IntuitionStore.services.promotion;

import com.ngoctin.intuition.IntuitionStore.entities.promotion.Promotion;
import com.ngoctin.intuition.IntuitionStore.repositories.promotion.PromotionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;

    @Override
    public List<Promotion> getPromotionsByUserID(int id) {
        List<Promotion> promotions = promotionRepository.getPromotionsByUserID(id);
        if(promotions != null){
            return promotions;
        }
        return null;
    }

    @Override
    public boolean setUsedForPromotionByUserID(int promotionId, int userId) {
        log.info("Service : pID : {} - uID : {}  ",promotionId,userId);
        return promotionRepository.setUsedForPromotionByUserID(promotionId,userId);
    }
}
