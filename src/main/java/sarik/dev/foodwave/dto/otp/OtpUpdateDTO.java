package sarik.dev.foodwave.dto.otp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sarik.dev.foodwave.entity.user.Otp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtpUpdateDTO {

    private String otpCode;
    private LocalDateTime expiresAt;

    // Mavjud `Otp` entity ob'ektini yangilash uchun
    public void updateEntity(Otp otp) {
        otp.setOtpCode(this.otpCode);
        otp.setExpiresAt(this.expiresAt);
    }
}
