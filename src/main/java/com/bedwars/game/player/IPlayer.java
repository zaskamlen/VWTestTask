package com.bedwars.game.player;

import com.bedwars.game.team.Team;

import java.util.UUID;

public interface IPlayer {

    String getName();
    UUID getUUID();
    org.bukkit.entity.Player getPlayer();
    Team getTeam();
    int getKills();
    int getDeaths();
    int getBedBreaks();
    void setTeam(Team team);
    void addKills(int amount);
    void removeKills(int amount);

    void addDeaths(int amount);
    void removeDeaths(int amount);

    void addBedBreaks(int amount);
    void removeBedBreaks(int amount);


}
