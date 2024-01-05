package com.bedwars.game.config;

import org.bukkit.Location;

import java.util.List;

public class TeamConfig implements Config {

    private final String teamIndex;
    private final Location bronze;
    private final Location iron;
    private final Location gold;
    private final Location spawn;
    private final Location villager;
    private final Location bedFirst;
    private final Location bedSecond;

    public TeamConfig(String teamIndex) {
        this.teamIndex = teamIndex;
        this.bronze = getLocation("teams." + teamIndex + ".generators.bronze");
        this.iron = getLocation("teams." + teamIndex + ".generators.iron");
        this.gold = getLocation("teams." + teamIndex + ".generators.gold");
        this.spawn = getLocation("teams." + teamIndex + ".spawn");
        this.villager = getLocation("teams." + teamIndex + ".villager");
        this.bedFirst = getLocation("teams." + teamIndex + ".bed.1");
        this.bedSecond = getLocation("teams." + teamIndex + ".bed.2");
    }

    public String getTeamIndex() {
        return teamIndex;
    }

    public Location getBronze() {
        return bronze;
    }

    public Location getIron() {
        return iron;
    }

    public Location getGold() {
        return gold;
    }

    public Location getSpawn() {
        return spawn;
    }

    public Location getVillager() {
        return villager;
    }

    public Location getBedFirst() {
        return bedFirst;
    }

    public Location getBedSecond() {
        return bedSecond;
    }
}
