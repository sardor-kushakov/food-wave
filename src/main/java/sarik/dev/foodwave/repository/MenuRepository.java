package sarik.dev.foodwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwave.entity.admin.Menu;
import sarik.dev.foodwave.entity.admin.Restaurant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MenuRepository extends JpaRepository<Menu, UUID> {

    List<Menu> findByRestaurant(Restaurant restaurant);

    Optional<Menu> findByNameAndRestaurant(String name, Restaurant restaurant);
}
