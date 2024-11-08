package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.coupon.CouponCreateDTO;
import sarik.dev.foodwave.dto.coupon.CouponDTO;
import sarik.dev.foodwave.dto.coupon.CouponUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface CouponService {

    CouponDTO createCoupon(CouponCreateDTO couponCreateDTO);

    CouponDTO getCouponById(UUID couponId);

    CouponDTO getCouponByCode(String code);

    List<CouponDTO> getAllActiveCoupons();

    CouponDTO updateCoupon(UUID couponId, CouponUpdateDTO couponUpdateDTO);

    void deleteCoupon(UUID couponId);

    void activateCoupon(UUID couponId);

    void deactivateCoupon(UUID couponId);

    boolean isCouponValid(UUID couponId);
}
