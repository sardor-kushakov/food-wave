package sarik.dev.foodwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwave.entity.admin.Restaurant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {

    List<Restaurant> findByCityAndIsDeleted(String city, boolean isDeleted);

    List<Restaurant> findByStateAndIsDeleted(String state, boolean isDeleted);

    Optional<Restaurant> findByNameAndIsDeleted(String name, boolean isDeleted);
}
