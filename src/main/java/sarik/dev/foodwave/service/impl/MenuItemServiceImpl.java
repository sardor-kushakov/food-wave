package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.menu.item.MenuItemCreateDTO;
import sarik.dev.foodwave.dto.menu.item.MenuItemDTO;
import sarik.dev.foodwave.dto.menu.item.MenuItemUpdateDTO;
import sarik.dev.foodwave.entity.admin.Menu;
import sarik.dev.foodwave.entity.admin.MenuItem;
import sarik.dev.foodwave.entity.user.Dish;
import sarik.dev.foodwave.repository.DishRepository;
import sarik.dev.foodwave.repository.MenuItemRepository;
import sarik.dev.foodwave.repository.MenuRepository;
import sarik.dev.foodwave.service.MenuItemService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final MenuRepository menuRepository;
    private final DishRepository dishRepository;

    @Override
    @Transactional
    public MenuItemDTO addMenuItem(MenuItemCreateDTO menuItemCreateDTO) {
        Menu menu = menuRepository.findById(menuItemCreateDTO.getMenuId())
                .orElseThrow(() -> new IllegalArgumentException("Menu not found"));

        Dish dish = dishRepository.findById(menuItemCreateDTO.getDishId())
                .orElseThrow(() -> new IllegalArgumentException("Dish not found"));

        MenuItem menuItem = MenuItem.builder()
                .menu(menu)
                .dish(dish)
                .price(menuItemCreateDTO.getPrice())
                .build();

        menuItemRepository.save(menuItem);
        return MenuItemDTO.fromEntity(menuItem);
    }

    @Override
    public MenuItemDTO getMenuItemById(UUID menuItemId) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new IllegalArgumentException("Menu item not found"));
        return MenuItemDTO.fromEntity(menuItem);
    }

    @Override
    public List<MenuItemDTO> getMenuItemsByMenu(UUID menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("Menu not found"));

        return menuItemRepository.findByMenu(menu).stream()
                .map(MenuItemDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MenuItemDTO updateMenuItem(UUID menuItemId, MenuItemUpdateDTO menuItemUpdateDTO) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new IllegalArgumentException("Menu item not found"));

        menuItem.setPrice(menuItemUpdateDTO.getPrice());
        menuItemRepository.save(menuItem);

        return MenuItemDTO.fromEntity(menuItem);
    }

    @Override
    @Transactional
    public void deleteMenuItem(UUID menuItemId) {
        if (menuItemRepository.existsById(menuItemId)) {
            menuItemRepository.deleteById(menuItemId);
        } else {
            throw new IllegalArgumentException("Menu item not found");
        }
    }
}
