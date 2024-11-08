package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.category.CategoryCreateDTO;
import sarik.dev.foodwave.dto.category.CategoryDTO;
import sarik.dev.foodwave.dto.category.CategoryUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    CategoryDTO createCategory(CategoryCreateDTO categoryCreateDTO);

    List<CategoryDTO> getAllActiveCategories();

    CategoryDTO getCategoryById(UUID categoryId);

    CategoryDTO updateCategory(UUID categoryId, CategoryUpdateDTO categoryUpdateDTO);

    void deleteCategory(UUID categoryId);

    void addDishToCategory(UUID categoryId, UUID dishId);

    void removeDishFromCategory(UUID categoryId, UUID dishId);
}
