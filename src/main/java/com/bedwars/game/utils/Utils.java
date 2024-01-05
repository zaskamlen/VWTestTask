package com.bedwars.game.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Utils {

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> color(List<String> messages) {
        return messages.stream().map(Utils::color).collect(Collectors.toList());
    }

    public static List<String> colorList(String... messages) {
        return Arrays.stream(messages).map(Utils::color).collect(Collectors.toList());
    }

    public static String[] color(String... messages) {
        return Arrays.stream(messages).map(Utils::color).toArray(String[]::new);
    }

    public static void sendActionBar(Player player, String message) {
        IChatBaseComponent iChatBaseComponent = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + color(message) + "\"}");
        sendPacket(player, new PacketPlayOutChat(iChatBaseComponent, (byte) 2));
    }

    public static void sendPacket(Player player, Packet packet) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public static void resetPlayer(Player player) {
        player.getInventory().clear();
        player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setExp(0);
        player.setLevel(0);
        player.setGameMode(GameMode.SURVIVAL);
        player.setItemInHand(null);
        player.getInventory().setArmorContents(new ItemStack[4]);
    }

    public static Location getLocationFromString(String input) {
        String[] parts = input.split(",");
        if (parts.length == 3 || parts.length == 5) {
            double x = Double.parseDouble(parts[0]);
            double y = Double.parseDouble(parts[1]);
            double z = Double.parseDouble(parts[2]);

            Location location = new Location(Bukkit.getWorld("world"), x, y, z);

            if (parts.length == 5) {
                float yaw = Float.parseFloat(parts[3]);
                float pitch = Float.parseFloat(parts[4]);
                location.setYaw(yaw);
                location.setPitch(pitch);
            }

            return location;
        } else {
            throw new IllegalArgumentException("Invalid location format: " + input);
        }
    }
    public static ItemStack getCustomHead(byte[] headBase64) {
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        PropertyMap propertyMap = profile.getProperties();
        if (propertyMap == null) {
            throw new IllegalStateException("Profile doesn't contain a property map");
        }
        byte[] encodedData = headBase64;
        propertyMap.put("textures", new Property("textures", new String(encodedData)));
        ItemStack head = new ItemStack(Material.SKULL_ITEM,1 , (short) 3);
        ItemMeta headMeta = head.getItemMeta();
        setGameProfile(headMeta, profile);
        head.setItemMeta(headMeta);

        return head;
    }

    public static ItemStack getCustomHead(String texture) {
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        PropertyMap propertyMap = profile.getProperties();
        if (propertyMap == null) {
            throw new IllegalStateException("Profile doesn't contain a property map");
        }
        propertyMap.put("textures", new Property("textures", texture));
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1 , (short) 3);
        ItemMeta headMeta = head.getItemMeta();
        setGameProfile(headMeta, profile);
        head.setItemMeta(headMeta);

        return head;
    }

    private static void setGameProfile(ItemMeta itemMeta, GameProfile profile) {
        try {
            Field profileField = itemMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(itemMeta, profile);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static ItemStack setItemMeta(ItemStack stack, String displayName, String... lore) {
        ItemMeta meta =stack.getItemMeta();

        meta.setLore(colorList(lore));
        meta.setDisplayName(color(displayName));

        stack.setItemMeta(meta);

        return stack;
    }
}
