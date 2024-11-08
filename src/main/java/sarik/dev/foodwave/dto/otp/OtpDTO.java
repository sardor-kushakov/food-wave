package sarik.dev.foodwave.dto.otp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sarik.dev.foodwave.entity.user.Otp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtpDTO {

    private UUID id;
    private String otpCode;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private UUID userId; // User ID qaytarish uchun

    public static OtpDTO fromEntity(Otp otp) {
        return new OtpDTO(
                otp.getId(),
                otp.getOtpCode(),
                otp.getCreatedAt(),
                otp.getExpiresAt(),
                otp.getUser().getId() // User ID ni olish
        );
    }
}
