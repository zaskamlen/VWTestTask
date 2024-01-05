package com.bedwars.game.generator;

import org.bukkit.Location;

public class GoldGenerator extends ItemGenerator{
    public GoldGenerator(Location spawnLocation) {
        super(240, ItemType.GOLD, spawnLocation);
    }
}
