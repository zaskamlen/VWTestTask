package com.bedwars.game.team.merchant;

import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

public abstract class Entity implements EntityManager {

    private final Location spawnLocation;
    private final String entityName;
    private static final Map<String, Entity> entityMap = new HashMap<>();

    public Entity(String entityName,Location spawnLocation) {
        this.spawnLocation = spawnLocation;
        this.entityName = entityName;
        entityMap.put(entityName,this);
    }

    public static Entity getEntity(String entityName) {
        return entityMap.get(entityName);
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public String getEntityName() {
        return entityName;
    }
}
