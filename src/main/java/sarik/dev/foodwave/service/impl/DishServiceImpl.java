package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.dish.DishCreateDTO;
import sarik.dev.foodwave.dto.dish.DishDTO;
import sarik.dev.foodwave.dto.dish.DishUpdateDTO;
import sarik.dev.foodwave.entity.admin.Category;
import sarik.dev.foodwave.entity.admin.Ingredient;
import sarik.dev.foodwave.entity.user.Dish;
import sarik.dev.foodwave.enums.user.DishType;
import sarik.dev.foodwave.repository.CategoryRepository;
import sarik.dev.foodwave.repository.DishRepository;
import sarik.dev.foodwave.repository.IngredientRepository;
import sarik.dev.foodwave.service.DishService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final CategoryRepository categoryRepository;
    private final IngredientRepository ingredientRepository;

    @Override
    @Transactional
    public DishDTO createDish(DishCreateDTO dishCreateDTO) {
        Category category = categoryRepository.findById(dishCreateDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        // DishType qiymatini olish
        DishType dishType = dishCreateDTO.getDishType();

        Dish dish = Dish.builder()
                .name(dishCreateDTO.getName())
                .description(dishCreateDTO.getDescription())
                .price(dishCreateDTO.getPrice())
                .imageUrl(dishCreateDTO.getImageUrl())
                .dishType(dishType) // To'g'ri enum qiymatini oling
                .isAvailable(dishCreateDTO.isAvailable())
                .category(category) // Kategoriya qo'shiladi
                .build();

        dishRepository.save(dish);
        return DishDTO.fromEntity(dish);
    }


    @Override
    public DishDTO getDishById(UUID dishId) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new IllegalArgumentException("Dish not found"));
        return DishDTO.fromEntity(dish);
    }

    @Override
    public List<DishDTO> getDishesByCategory(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        return dishRepository.findByCategoryAndIsAvailable(category, true).stream()
                .map(DishDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<DishDTO> getDishesByType(String dishType) {
        DishType type = DishType.valueOf(dishType);
        return dishRepository.findByDishTypeAndIsAvailable(type, true).stream()
                .map(DishDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<DishDTO> getAvailableDishes() {
        return dishRepository.findByIsAvailable(true).stream()
                .map(DishDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DishDTO updateDish(UUID dishId, DishUpdateDTO dishUpdateDTO) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new IllegalArgumentException("Dish not found"));

        dish.setName(dishUpdateDTO.getName());
        dish.setDescription(dishUpdateDTO.getDescription());
        dish.setPrice(dishUpdateDTO.getPrice());
        dish.setImageUrl(dishUpdateDTO.getImageUrl());
        dish.setDishType(dishUpdateDTO.getDishType()); // Enum qiymatini to'g'ridan-to'g'ri oling

        dishRepository.save(dish);
        return DishDTO.fromEntity(dish);
    }

    @Override
    @Transactional
    public void deleteDish(UUID dishId) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new IllegalArgumentException("Dish not found"));
        dishRepository.delete(dish);
    }

    @Override
    @Transactional
    public void addIngredientToDish(UUID dishId, UUID ingredientId) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new IllegalArgumentException("Dish not found"));

        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found"));

        dish.addIngredient(ingredient);
        dishRepository.save(dish);
    }

    @Override
    @Transactional
    public void removeIngredientFromDish(UUID dishId, UUID ingredientId) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new IllegalArgumentException("Dish not found"));

        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found"));

        dish.removeIngredient(ingredient);
        dishRepository.save(dish);
    }
}
