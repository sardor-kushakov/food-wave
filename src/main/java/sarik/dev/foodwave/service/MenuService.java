package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.menu.MenuCreateDTO;
import sarik.dev.foodwave.dto.menu.MenuDTO;
import sarik.dev.foodwave.dto.menu.MenuUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface MenuService {

    MenuDTO createMenu(MenuCreateDTO menuCreateDTO);

    MenuDTO getMenuById(UUID menuId);

    List<MenuDTO> getMenusByRestaurant(UUID restaurantId);

    MenuDTO updateMenu(UUID menuId, MenuUpdateDTO menuUpdateDTO);

    void deleteMenu(UUID menuId);

    void addMenuItem(UUID menuId, UUID itemId);

    void removeMenuItem(UUID menuId, UUID itemId);
}
