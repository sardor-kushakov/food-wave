package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.coupon.CouponCreateDTO;
import sarik.dev.foodwave.dto.coupon.CouponDTO;
import sarik.dev.foodwave.dto.coupon.CouponUpdateDTO;
import sarik.dev.foodwave.entity.admin.Coupon;
import sarik.dev.foodwave.repository.CouponRepository;
import sarik.dev.foodwave.service.CouponService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Override
    @Transactional
    public CouponDTO createCoupon(CouponCreateDTO couponCreateDTO) {
        Coupon coupon = Coupon.builder()
                .code(couponCreateDTO.getCode())
                .discountAmount(couponCreateDTO.getDiscountAmount())
                .discountPercentage(couponCreateDTO.getDiscountPercentage())
                .isActive(true)
                .validFrom(couponCreateDTO.getValidFrom())
                .validUntil(couponCreateDTO.getValidUntil())
                .build();

        couponRepository.save(coupon);
        return CouponDTO.fromEntity(coupon);
    }

    @Override
    public CouponDTO getCouponById(UUID couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new IllegalArgumentException("Coupon not found"));
        return CouponDTO.fromEntity(coupon);
    }

    @Override
    public CouponDTO getCouponByCode(String code) {
        Coupon coupon = couponRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Coupon with code not found"));
        return CouponDTO.fromEntity(coupon);
    }

    @Override
    public List<CouponDTO> getAllActiveCoupons() {
        return couponRepository.findByIsActiveAndValidUntilAfter(true, LocalDateTime.now()).stream()
                .map(CouponDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CouponDTO updateCoupon(UUID couponId, CouponUpdateDTO couponUpdateDTO) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new IllegalArgumentException("Coupon not found"));

        coupon.setCode(couponUpdateDTO.getCode());
        coupon.setDiscountAmount(couponUpdateDTO.getDiscountAmount());
        coupon.setDiscountPercentage(couponUpdateDTO.getDiscountPercentage());
        coupon.setValidFrom(couponUpdateDTO.getValidFrom());
        coupon.setValidUntil(couponUpdateDTO.getValidUntil());

        couponRepository.save(coupon);
        return CouponDTO.fromEntity(coupon);
    }

    @Override
    @Transactional
    public void deleteCoupon(UUID couponId) {
        if (couponRepository.existsById(couponId)) {
            couponRepository.deleteById(couponId);
        } else {
            throw new IllegalArgumentException("Coupon not found");
        }
    }

    @Override
    @Transactional
    public void activateCoupon(UUID couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new IllegalArgumentException("Coupon not found"));
        coupon.activate();
        couponRepository.save(coupon);
    }

    @Override
    @Transactional
    public void deactivateCoupon(UUID couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new IllegalArgumentException("Coupon not found"));
        coupon.deactivate();
        couponRepository.save(coupon);
    }

    @Override
    public boolean isCouponValid(UUID couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new IllegalArgumentException("Coupon not found"));
        return coupon.isValid();
    }
}
