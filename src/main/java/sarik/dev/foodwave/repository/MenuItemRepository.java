package sarik.dev.foodwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwave.entity.admin.Menu;
import sarik.dev.foodwave.entity.admin.MenuItem;
import sarik.dev.foodwave.entity.user.Dish;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, UUID> {

    List<MenuItem> findByMenu(Menu menu);

    Optional<MenuItem> findByMenuAndDish(Menu menu, Dish dish);
}
