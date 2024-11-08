package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.menu.item.MenuItemCreateDTO;
import sarik.dev.foodwave.dto.menu.item.MenuItemDTO;
import sarik.dev.foodwave.dto.menu.item.MenuItemUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface MenuItemService {

    MenuItemDTO addMenuItem(MenuItemCreateDTO menuItemCreateDTO);

    MenuItemDTO getMenuItemById(UUID menuItemId);

    List<MenuItemDTO> getMenuItemsByMenu(UUID menuId);

    MenuItemDTO updateMenuItem(UUID menuItemId, MenuItemUpdateDTO menuItemUpdateDTO);

    void deleteMenuItem(UUID menuItemId);
}
