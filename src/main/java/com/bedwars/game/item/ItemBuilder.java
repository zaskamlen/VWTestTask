package com.bedwars.game.item;

import com.bedwars.game.utils.Utils;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemBuilder {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    public ItemBuilder(Material material,int id) {
        this.itemStack = new ItemStack(material,1, (short) id);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder setName(String name) {
        itemMeta.setDisplayName(Utils.color(name));
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        itemMeta.setLore(Utils.colorList(lore));
        return this;
    }

    public ItemBuilder addLoreLine(String loreLine) {
        List<String> lore = itemMeta.getLore();
        if (lore == null) {
            lore = new ArrayList<>();
        }
        lore.add(loreLine);
        itemMeta.setLore(Utils.colorList(loreLine));
        return this;
    }

    public ItemBuilder setColor(Color color) {
        LeatherArmorMeta armorMeta = (LeatherArmorMeta) itemMeta;
        armorMeta.setColor(color);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        itemMeta.spigot().setUnbreakable(unbreakable);
        return this;
    }



    public ItemBuilder setAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}