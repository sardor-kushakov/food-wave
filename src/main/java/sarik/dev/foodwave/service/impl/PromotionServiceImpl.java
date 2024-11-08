package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.promation.PromotionCreateDTO;
import sarik.dev.foodwave.dto.promation.PromotionDTO;
import sarik.dev.foodwave.dto.promation.PromotionUpdateDTO;
import sarik.dev.foodwave.entity.admin.Promotion;
import sarik.dev.foodwave.repository.PromotionRepository;
import sarik.dev.foodwave.service.PromotionService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;

    @Override
    @Transactional
    public PromotionDTO createPromotion(PromotionCreateDTO promotionCreateDTO) {
        Promotion promotion = Promotion.builder()
                .name(promotionCreateDTO.getName())
                .description(promotionCreateDTO.getDescription())
                .discountPercentage(promotionCreateDTO.getDiscountPercentage())
                .isActive(true)
                .validFrom(promotionCreateDTO.getValidFrom())
                .validUntil(promotionCreateDTO.getValidUntil())
                .build();

        promotionRepository.save(promotion);
        return PromotionDTO.fromEntity(promotion);
    }

    @Override
    public PromotionDTO getPromotionById(UUID promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new IllegalArgumentException("Promotion not found"));
        return PromotionDTO.fromEntity(promotion);
    }

    @Override
    public List<PromotionDTO> getAllActivePromotions() {
        return promotionRepository.findByIsActiveAndValidUntilAfter(true, LocalDateTime.now()).stream()
                .map(PromotionDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PromotionDTO updatePromotion(UUID promotionId, PromotionUpdateDTO promotionUpdateDTO) {
        Promotion promotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new IllegalArgumentException("Promotion not found"));

        promotion.setName(promotionUpdateDTO.getName());
        promotion.setDescription(promotionUpdateDTO.getDescription());
        promotion.setDiscountPercentage(promotionUpdateDTO.getDiscountPercentage());
        promotion.setValidFrom(promotionUpdateDTO.getValidFrom());
        promotion.setValidUntil(promotionUpdateDTO.getValidUntil());

        promotionRepository.save(promotion);
        return PromotionDTO.fromEntity(promotion);
    }

    @Override
    @Transactional
    public void deletePromotion(UUID promotionId) {
        if (promotionRepository.existsById(promotionId)) {
            promotionRepository.deleteById(promotionId);
        } else {
            throw new IllegalArgumentException("Promotion not found");
        }
    }

    @Override
    @Transactional
    public void activatePromotion(UUID promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new IllegalArgumentException("Promotion not found"));
        promotion.activate();
        promotionRepository.save(promotion);
    }

    @Override
    @Transactional
    public void deactivatePromotion(UUID promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new IllegalArgumentException("Promotion not found"));
        promotion.deactivate();
        promotionRepository.save(promotion);
    }

    @Override
    public boolean isPromotionValid(UUID promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new IllegalArgumentException("Promotion not found"));
        return promotion.isValid();
    }
}
