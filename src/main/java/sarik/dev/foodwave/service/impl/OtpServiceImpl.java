package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.otp.OtpCreateDTO;
import sarik.dev.foodwave.dto.otp.OtpDTO;
import sarik.dev.foodwave.entity.user.Otp;
import sarik.dev.foodwave.entity.user.User;
import sarik.dev.foodwave.repository.OtpRepository;
import sarik.dev.foodwave.repository.UserRepository;
import sarik.dev.foodwave.service.OtpService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final OtpRepository otpRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public OtpDTO generateOtp(OtpCreateDTO otpCreateDTO) {
        User user = userRepository.findById(otpCreateDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Otp otp = Otp.generateOtp(user, otpCreateDTO.getOtpCode(), otpCreateDTO.getExpiryMinutes());
        otpRepository.save(otp);
        return OtpDTO.fromEntity(otp);
    }

    @Override
    public OtpDTO getOtpByCode(String otpCode) {
        Otp otp = otpRepository.findByOtpCode(otpCode)
                .orElseThrow(() -> new IllegalArgumentException("OTP not found"));
        return OtpDTO.fromEntity(otp);
    }

    @Override
    @Transactional
    public boolean verifyOtp(UUID userId, String otpCode) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Otp otp = otpRepository.findByUserAndOtpCode(user, otpCode)
                .orElseThrow(() -> new IllegalArgumentException("OTP not found or expired"));

        if (otp.isExpired()) {
            otpRepository.delete(otp);
            return false;
        }

        otpRepository.delete(otp); // OTP ni faqat bir marta ishlatish mumkin
        return true;
    }

    @Override
    @Transactional
    public void deleteOtp(UUID otpId) {
        otpRepository.deleteById(otpId);
    }

    @Override
    @Transactional
    public void deleteExpiredOtps() {
        otpRepository.findAll().stream()
                .filter(Otp::isExpired)
                .forEach(otpRepository::delete);
    }
}
