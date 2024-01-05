package com.bedwars.game.player;

import com.bedwars.game.team.Team;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GamePlayer implements IPlayer {

    private final String username;
    private final UUID uuid;
    private final Player player;
    private int kills;
    private int deaths;
    private int bedBreaks;
    private Team team;

    public GamePlayer(Player player) {
        this.username = player.getDisplayName();
        this.uuid = player.getUniqueId();
        this.player = player;
        this.kills = 0;
        this.bedBreaks = 0;
        this.deaths = 0;
    }

    @Override
    public int getKills() {
        return this.kills;
    }

    @Override
    public int getDeaths() {
        return this.kills;
    }

    @Override
    public int getBedBreaks() {
        return this.bedBreaks;
    }

    @Override
    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public void addKills(int amount) {
        kills += amount;
    }

    @Override
    public void removeKills(int amount) {
        kills = Math.max(0, kills - amount);
    }

    @Override
    public void addDeaths(int amount) {
        deaths += amount;
    }

    @Override
    public void removeDeaths(int amount) {
        deaths = Math.max(0, deaths - amount);
    }

    @Override
    public void addBedBreaks(int amount) {
        bedBreaks += amount;
    }

    @Override
    public void removeBedBreaks(int amount) {
        bedBreaks = Math.max(0, bedBreaks - amount);
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setBedBreaks(int bedBreaks) {
        this.bedBreaks = bedBreaks;
    }

    @Override
    public String getName() {
        return this.username;
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public Team getTeam() {
        return team;
    }
}
