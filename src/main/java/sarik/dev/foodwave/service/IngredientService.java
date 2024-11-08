package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.ingredient.IngredientCreateDTO;
import sarik.dev.foodwave.dto.ingredient.IngredientDTO;
import sarik.dev.foodwave.dto.ingredient.IngredientUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface IngredientService {

    IngredientDTO createIngredient(IngredientCreateDTO ingredientCreateDTO);

    IngredientDTO getIngredientById(UUID ingredientId);

    List<IngredientDTO> getAllIngredients();

    IngredientDTO updateIngredient(UUID ingredientId, IngredientUpdateDTO ingredientUpdateDTO);

    void deleteIngredient(UUID ingredientId);

    void addIngredientToDish(UUID ingredientId, UUID dishId);

    void removeIngredientFromDish(UUID ingredientId, UUID dishId);
}
