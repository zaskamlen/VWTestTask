package com.bedwars.game.generator;

import org.bukkit.Material;

public enum ItemType {

    BRONZE(Material.CLAY_BRICK,"&cБронза"),IRON(Material.IRON_INGOT,"&7Железо"), GOLD(Material.GOLD_INGOT,"&eЗолото");

    private final Material itemType;
    private final String itemName;

    ItemType(Material itemType,String itemName) {
        this.itemType = itemType;
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public Material getItemType() {
        return itemType;
    }
}
