package com.bedwars.game.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public interface MenuManager {

    void open(Player player);
    void setItem(int slot, ItemStack item);
    void addItem(ItemStack... items);
    void onClick(int slot, ItemStack item, Player player);



}
