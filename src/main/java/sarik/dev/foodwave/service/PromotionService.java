package sarik.dev.foodwave.service;


import sarik.dev.foodwave.dto.promation.PromotionCreateDTO;
import sarik.dev.foodwave.dto.promation.PromotionDTO;
import sarik.dev.foodwave.dto.promation.PromotionUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface PromotionService {

    PromotionDTO createPromotion(PromotionCreateDTO promotionCreateDTO);

    PromotionDTO getPromotionById(UUID promotionId);

    List<PromotionDTO> getAllActivePromotions();

    PromotionDTO updatePromotion(UUID promotionId, PromotionUpdateDTO promotionUpdateDTO);

    void deletePromotion(UUID promotionId);

    void activatePromotion(UUID promotionId);

    void deactivatePromotion(UUID promotionId);

    boolean isPromotionValid(UUID promotionId);
}
