package com.bedwars.game.generator;

import com.bedwars.BedWars;
import com.bedwars.game.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Bed;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class ItemGenerator {

    private final long ticks;
    private final ItemType type;
    private final Location spawnLocation;

    protected ItemGenerator(long ticks, ItemType type, Location spawnLocation) {
        this.ticks = ticks;
        this.type = type;
        this.spawnLocation = spawnLocation;
    }

    public void spawn() {
        World world = spawnLocation.getWorld();
        Bukkit.getScheduler().runTaskTimer(BedWars.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                world.dropItem(spawnLocation, new ItemBuilder(type.getItemType()).setAmount(1).setName(type.getItemName()).build());
            }
        },ticks,ticks);
    }

    public long getTicks() {
        return ticks;
    }

    public ItemType getType() {
        return type;
    }
}
