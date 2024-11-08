package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.otp.OtpCreateDTO;
import sarik.dev.foodwave.dto.otp.OtpDTO;

import java.util.UUID;

public interface OtpService {

    OtpDTO generateOtp(OtpCreateDTO otpCreateDTO);

    OtpDTO getOtpByCode(String otpCode);

    boolean verifyOtp(UUID userId, String otpCode);

    void deleteOtp(UUID otpId);

    void deleteExpiredOtps();
}
