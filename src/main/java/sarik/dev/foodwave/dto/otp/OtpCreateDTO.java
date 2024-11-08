package sarik.dev.foodwave.dto.otp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sarik.dev.foodwave.entity.user.Otp;
import sarik.dev.foodwave.entity.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtpCreateDTO {

    private UUID userId;
    private String otpCode;
    private int expiryMinutes; // Yaroqlilik muddati (minutlarda)

    // `Otp` entity ga aylantiruvchi metod
    public Otp toEntity(User user) {
        return Otp.builder()
                .otpCode(this.otpCode)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(expiryMinutes))
                .user(user)
                .build();
    }
}
