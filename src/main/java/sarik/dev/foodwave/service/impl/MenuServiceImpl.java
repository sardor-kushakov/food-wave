package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.menu.MenuCreateDTO;
import sarik.dev.foodwave.dto.menu.MenuDTO;
import sarik.dev.foodwave.dto.menu.MenuUpdateDTO;
import sarik.dev.foodwave.entity.admin.Menu;
import sarik.dev.foodwave.entity.admin.MenuItem;
import sarik.dev.foodwave.entity.admin.Restaurant;
import sarik.dev.foodwave.repository.MenuItemRepository;
import sarik.dev.foodwave.repository.MenuRepository;
import sarik.dev.foodwave.repository.RestaurantRepository;
import sarik.dev.foodwave.service.MenuService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    @Transactional
    public MenuDTO createMenu(MenuCreateDTO menuCreateDTO) {
        Restaurant restaurant = restaurantRepository.findById(menuCreateDTO.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        Menu menu = Menu.builder()
                .name(menuCreateDTO.getName())
                .description(menuCreateDTO.getDescription())
                .restaurant(restaurant)
                .build();

        menuRepository.save(menu);
        return MenuDTO.fromEntity(menu);
    }

    @Override
    public MenuDTO getMenuById(UUID menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("Menu not found"));
        return MenuDTO.fromEntity(menu);
    }

    @Override
    public List<MenuDTO> getMenusByRestaurant(UUID restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found"));

        return menuRepository.findByRestaurant(restaurant).stream()
                .map(MenuDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MenuDTO updateMenu(UUID menuId, MenuUpdateDTO menuUpdateDTO) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("Menu not found"));

        menu.setName(menuUpdateDTO.getName());
        menu.setDescription(menuUpdateDTO.getDescription());
        menuRepository.save(menu);

        return MenuDTO.fromEntity(menu);
    }

    @Override
    @Transactional
    public void deleteMenu(UUID menuId) {
        if (menuRepository.existsById(menuId)) {
            menuRepository.deleteById(menuId);
        } else {
            throw new IllegalArgumentException("Menu not found");
        }
    }

    @Override
    @Transactional
    public void addMenuItem(UUID menuId, UUID itemId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("Menu not found"));

        MenuItem item = menuItemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Menu item not found"));

        menu.addItem(item);
        menuRepository.save(menu);
    }

    @Override
    @Transactional
    public void removeMenuItem(UUID menuId, UUID itemId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("Menu not found"));

        MenuItem item = menuItemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Menu item not found"));

        menu.removeItem(item);
        menuRepository.save(menu);
    }
}
