package sarik.dev.foodwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwave.entity.user.Otp;
import sarik.dev.foodwave.entity.user.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OtpRepository extends JpaRepository<Otp, UUID> {

    Optional<Otp> findByOtpCode(String otpCode);

    Optional<Otp> findByUserAndOtpCode(User user, String otpCode);
}
