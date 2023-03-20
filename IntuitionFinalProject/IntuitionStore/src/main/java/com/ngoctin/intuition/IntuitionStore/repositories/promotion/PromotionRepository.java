package com.ngoctin.intuition.IntuitionStore.repositories.promotion;

import com.ngoctin.intuition.IntuitionStore.entities.promotion.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Long> , MyPromotionRepository{
}
