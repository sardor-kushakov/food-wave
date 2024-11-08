package sarik.dev.foodwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sarik.dev.foodwave.entity.user.Dish;
import sarik.dev.foodwave.entity.admin.Category;
import sarik.dev.foodwave.enums.user.DishType;

import java.util.List;
import java.util.UUID;

@Repository
public interface DishRepository extends JpaRepository<Dish, UUID> {

    List<Dish> findByCategoryAndIsAvailable(Category category, boolean isAvailable);

    List<Dish> findByDishTypeAndIsAvailable(DishType dishType, boolean isAvailable);

    List<Dish> findByIsAvailable(boolean isAvailable);
}
