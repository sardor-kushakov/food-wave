package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.category.CategoryCreateDTO;
import sarik.dev.foodwave.dto.category.CategoryDTO;
import sarik.dev.foodwave.dto.category.CategoryUpdateDTO;
import sarik.dev.foodwave.entity.admin.Category;
import sarik.dev.foodwave.entity.user.Dish;
import sarik.dev.foodwave.repository.CategoryRepository;
import sarik.dev.foodwave.repository.DishRepository;
import sarik.dev.foodwave.service.CategoryService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final DishRepository dishRepository;

    @Override
    @Transactional
    public CategoryDTO createCategory(CategoryCreateDTO categoryCreateDTO) {
        Category category = Category.builder()
                .name(categoryCreateDTO.getName())
                .description(categoryCreateDTO.getDescription())
                .build();
        categoryRepository.save(category);
        return CategoryDTO.fromEntity(category);
    }

    @Override
    public List<CategoryDTO> getAllActiveCategories() {
        return categoryRepository.findAllActiveCategories().stream()
                .map(CategoryDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(UUID categoryId) {
        Category category = categoryRepository.findByIdAndNotDeleted(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found or has been deleted"));
        return CategoryDTO.fromEntity(category);
    }

    @Override
    @Transactional
    public CategoryDTO updateCategory(UUID categoryId, CategoryUpdateDTO categoryUpdateDTO) {
        Category category = categoryRepository.findByIdAndNotDeleted(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found or has been deleted"));

        category.setName(categoryUpdateDTO.getName());
        category.setDescription(categoryUpdateDTO.getDescription());
        categoryRepository.save(category);
        
        return CategoryDTO.fromEntity(category);
    }

    @Override
    @Transactional
    public void deleteCategory(UUID categoryId) {
        Category category = categoryRepository.findByIdAndNotDeleted(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found or has been deleted"));
        categoryRepository.delete(category);
    }

    @Override
    @Transactional
    public void addDishToCategory(UUID categoryId, UUID dishId) {
        Category category = categoryRepository.findByIdAndNotDeleted(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found or has been deleted"));
        
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new IllegalArgumentException("Dish not found"));

        category.addDish(dish);
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void removeDishFromCategory(UUID categoryId, UUID dishId) {
        Category category = categoryRepository.findByIdAndNotDeleted(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found or has been deleted"));
        
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new IllegalArgumentException("Dish not found"));

        category.removeDish(dish);
        categoryRepository.save(category);
    }
}
