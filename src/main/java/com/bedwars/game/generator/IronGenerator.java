package com.bedwars.game.generator;

import org.bukkit.Location;

public class IronGenerator extends ItemGenerator{
    public IronGenerator(Location spawnLocation) {
        super(100, ItemType.IRON, spawnLocation);
    }
}
