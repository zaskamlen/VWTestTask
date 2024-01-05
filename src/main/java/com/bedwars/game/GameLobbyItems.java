package com.bedwars.game;

import com.bedwars.game.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class GameLobbyItems {

    public static void give(Player player) {
        player.getInventory().setItem(0,new ItemBuilder(Material.NAME_TAG).setName("&f&l>> &eВыбрать команду &f&l<<").setLore("VimeWorld.com").setAmount(1).build());
    }

}
