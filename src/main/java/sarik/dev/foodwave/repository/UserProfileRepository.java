package sarik.dev.foodwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwave.entity.user.UserProfile;
import sarik.dev.foodwave.entity.user.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {

    Optional<UserProfile> findByUser(User user);

    Optional<UserProfile> findByUserIdAndIsVerified(UUID userId, boolean isVerified);
}
