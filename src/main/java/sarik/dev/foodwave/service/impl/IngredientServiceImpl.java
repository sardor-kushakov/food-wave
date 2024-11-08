package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.ingredient.IngredientCreateDTO;
import sarik.dev.foodwave.dto.ingredient.IngredientDTO;
import sarik.dev.foodwave.dto.ingredient.IngredientUpdateDTO;
import sarik.dev.foodwave.entity.admin.Ingredient;
import sarik.dev.foodwave.entity.user.Dish;
import sarik.dev.foodwave.repository.DishRepository;
import sarik.dev.foodwave.repository.IngredientRepository;
import sarik.dev.foodwave.service.IngredientService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final DishRepository dishRepository;

    @Override
    @Transactional
    public IngredientDTO createIngredient(IngredientCreateDTO ingredientCreateDTO) {
        Ingredient ingredient = Ingredient.builder()
                .ingredientName(ingredientCreateDTO.getIngredientName())
                .unit(ingredientCreateDTO.getUnit())
                .build();

        ingredientRepository.save(ingredient);
        return IngredientDTO.fromEntity(ingredient);
    }

    @Override
    public IngredientDTO getIngredientById(UUID ingredientId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found"));
        return IngredientDTO.fromEntity(ingredient);
    }

    @Override
    public List<IngredientDTO> getAllIngredients() {
        return ingredientRepository.findAll().stream()
                .map(IngredientDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public IngredientDTO updateIngredient(UUID ingredientId, IngredientUpdateDTO ingredientUpdateDTO) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found"));

        ingredient.setIngredientName(ingredientUpdateDTO.getIngredientName());
        ingredient.setUnit(ingredientUpdateDTO.getUnit());
        ingredientRepository.save(ingredient);
        
        return IngredientDTO.fromEntity(ingredient);
    }

    @Override
    @Transactional
    public void deleteIngredient(UUID ingredientId) {
        if (ingredientRepository.existsById(ingredientId)) {
            ingredientRepository.deleteById(ingredientId);
        } else {
            throw new IllegalArgumentException("Ingredient not found");
        }
    }

    @Override
    @Transactional
    public void addIngredientToDish(UUID ingredientId, UUID dishId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found"));
        
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new IllegalArgumentException("Dish not found"));

        ingredient.addDish(dish);
        ingredientRepository.save(ingredient);
    }

    @Override
    @Transactional
    public void removeIngredientFromDish(UUID ingredientId, UUID dishId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found"));
        
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new IllegalArgumentException("Dish not found"));

        ingredient.removeDish(dish);
        ingredientRepository.save(ingredient);
    }
}
