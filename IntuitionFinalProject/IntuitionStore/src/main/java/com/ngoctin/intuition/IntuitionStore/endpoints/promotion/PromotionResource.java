package com.ngoctin.intuition.IntuitionStore.endpoints.promotion;

import com.ngoctin.intuition.IntuitionStore.entities.promotion.Promotion;
import com.ngoctin.intuition.IntuitionStore.models.user.PrincipalResponse;
import com.ngoctin.intuition.IntuitionStore.services.promotion.PromotionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/promotion")
@RestController
@RequiredArgsConstructor
@Slf4j
public class PromotionResource {

    private final PromotionService promotionService;

    @GetMapping("/getPromotionByUserID/{userID}")
    public ResponseEntity<?> getPromotionByUserID(@PathVariable("userID") int id){
        List<Promotion> promotions = promotionService.getPromotionsByUserID(id);
        if(promotions != null){
            return ResponseEntity.status(HttpStatus.OK).body(promotions);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Empty Promotions !");
    }

    @PutMapping("/setUserPromotionByUserID/{promotionID}/{userID}")
    public ResponseEntity<?> setUserPromotionByUserID( @PathVariable("promotionID") int promotionID ,@PathVariable("userID") int userId){
        log.info("pID : {} - uID : {}  ",promotionID,userId);
        if(promotionService.setUsedForPromotionByUserID(promotionID,userId)){
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(false);
    }

}
