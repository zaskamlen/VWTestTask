package com.bedwars.game.config;

import org.bukkit.Location;

public class GameConfig implements Config {
    private final String mapName;
    private final Location spawn;
    private final String host;
    private final String database;
    private final String password;
    private final String username;

    public GameConfig() {
        this.host = configuration.getString("mysql.host");
        this.database = configuration.getString("mysql.database");
        this.password = configuration.getString("mysql.password");
        this.username = configuration.getString("mysql.username");
        this.mapName = configuration.getString("name");
        this.spawn = getLocation("spawn");
        this.teamInfo.put("red",new TeamConfig("red"));
        this.teamInfo.put("blue",new TeamConfig("blue"));
    }

    public String getDatabase() {
        return database;
    }

    public String getHost() {
        return host;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Location getSpawn() {
        return spawn;
    }

    public String getMapName() {
        return mapName;
    }
}
