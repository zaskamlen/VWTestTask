package com.bedwars.game.config;

import com.bedwars.BedWars;
import com.bedwars.game.team.bed.Bed;
import com.bedwars.game.utils.Utils;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface Config {

    FileConfiguration configuration = BedWars.getInstance().getConfig();
    Map<String,TeamConfig> teamInfo = new ConcurrentHashMap<>();

    default Location getLocation(String input) {
        return Utils.getLocationFromString(configuration.getString(input));
    }

}
