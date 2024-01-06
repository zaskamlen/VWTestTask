package com.bedwars.game.player;

import com.bedwars.game.player.stats.NBT;
import com.bedwars.game.team.Team;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GamePlayer implements IPlayer {

    private final String username;
    private final UUID uuid;
    private final Player player;
    private final NBT stats;
    private Team team;

    public GamePlayer(Player player) {
        this.username = player.getDisplayName();
        this.uuid = player.getUniqueId();
        this.player = player;
        this.stats = new NBT();
        this.stats.set("kills",0);
        this.stats.set("deaths",0);
        this.stats.set("beds",0);
    }

    @Override
    public int getKills() {
        return this.stats.getInt("kills");
    }

    @Override
    public int getDeaths() {
        return this.stats.getInt("deaths");
    }

    @Override
    public int getBedBreaks() {
        return this.stats.getInt("beds");
    }

    @Override
    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public void addKills(int amount) {
        int kills = stats.getInt("kills");
        this.stats.set("kills", kills + amount);
    }

    @Override
    public void removeKills(int amount) {
        int kills = stats.getInt("kills");
        this.stats.set("kills", kills - amount);
    }

    @Override
    public void addDeaths(int amount) {
        int deaths = stats.getInt("deaths");
        this.stats.set("deaths", deaths + amount);
    }

    @Override
    public void removeDeaths(int amount) {
        int deaths = stats.getInt("deaths");
        this.stats.set("deaths", deaths - amount);
    }

    @Override
    public void addBedBreaks(int amount) {
        int beds = stats.getInt("beds");
        this.stats.set("beds", beds + amount);
    }

    @Override
    public void removeBedBreaks(int amount) {
        int beds = stats.getInt("beds");
        this.stats.set("beds", beds - amount);
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
