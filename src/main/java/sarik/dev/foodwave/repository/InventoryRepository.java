package sarik.dev.foodwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwave.entity.admin.Inventory;
import sarik.dev.foodwave.entity.admin.Restaurant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, UUID> {

    Optional<Inventory> findByIngredientNameAndRestaurant(String ingredientName, Restaurant restaurant);

    List<Inventory> findByRestaurant(Restaurant restaurant);
}
