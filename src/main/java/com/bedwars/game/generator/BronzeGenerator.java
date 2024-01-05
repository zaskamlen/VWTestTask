package com.bedwars.game.generator;

import org.bukkit.Location;

public class BronzeGenerator extends ItemGenerator{
    public BronzeGenerator(Location spawnLocation) {
        super(20, ItemType.BRONZE, spawnLocation);
    }
}
