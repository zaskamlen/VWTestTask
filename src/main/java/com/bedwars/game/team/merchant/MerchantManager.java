package com.bedwars.game.team.merchant;

import com.bedwars.game.generator.ItemType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public interface MerchantManager {

    void buy(Player player,ItemStack item, ItemType type, int price);

    default int getCountItem(Player player, Material material) {
        return Arrays.stream(player.getInventory().getContents())
                .filter(item -> item != null && item.getType() == material)
                .mapToInt(ItemStack::getAmount).sum();
    }
    default void removeItem(Player player, Material material, int amount) {
        int remainingAmount = amount;

        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType() == material) {
                int itemAmount = item.getAmount();

                if (itemAmount <= remainingAmount) {
                    remainingAmount -= itemAmount;
                    player.getInventory().remove(item);
                } else {
                    item.setAmount(itemAmount - remainingAmount);
                    break;
                }
            }
        }
    }

}
