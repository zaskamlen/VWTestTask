package com.bedwars.game.menu;

import com.bedwars.game.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class MenuListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory() == null) {
            return;
        }

        String inventoryTitle = event.getView().getTitle();

        Menu menuManager = Menu.getMenu(inventoryTitle);
        if (menuManager == null) {
            return;
        }

        int slot = event.getRawSlot();
        if (slot >= 0 && slot < event.getInventory().getSize()) {
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem != null) {
                menuManager.onClick(slot, clickedItem, player);
                event.setCancelled(true);
            }
        }
    }
}