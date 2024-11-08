package sarik.dev.foodwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwave.entity.admin.RestaurantOwner;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestaurantOwnerRepository extends JpaRepository<RestaurantOwner, UUID> {

    Optional<RestaurantOwner> findByEmail(String email);

    List<RestaurantOwner> findByIsVerified(boolean isVerified);
}
