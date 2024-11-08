package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.dish.DishCreateDTO;
import sarik.dev.foodwave.dto.dish.DishDTO;
import sarik.dev.foodwave.dto.dish.DishUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface DishService {

    DishDTO createDish(DishCreateDTO dishCreateDTO);

    DishDTO getDishById(UUID dishId);

    List<DishDTO> getDishesByCategory(UUID categoryId);

    List<DishDTO> getDishesByType(String dishType);

    List<DishDTO> getAvailableDishes();

    DishDTO updateDish(UUID dishId, DishUpdateDTO dishUpdateDTO);

    void deleteDish(UUID dishId);

    void addIngredientToDish(UUID dishId, UUID ingredientId);

    void removeIngredientFromDish(UUID dishId, UUID ingredientId);
}
