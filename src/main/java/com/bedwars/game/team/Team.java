package com.bedwars.game.team;

import com.bedwars.game.config.GameConfig;
import com.bedwars.game.config.TeamConfig;
import com.bedwars.game.generator.BronzeGenerator;
import com.bedwars.game.generator.GoldGenerator;
import com.bedwars.game.generator.IronGenerator;
import com.bedwars.game.player.GamePlayer;
import com.bedwars.game.team.bed.Bed;
import com.bedwars.game.team.merchant.Entity;
import com.bedwars.game.team.merchant.Merchant;
import com.bedwars.game.utils.Utils;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.LinkedList;

public abstract class Team implements TeamManager {

    private final org.bukkit.Color color;
    private final LinkedList<GamePlayer> players;
    private final Location spawnLocation;
    private final Merchant villager;
    private final Bed bed;
    private final String teamIndex;
    private final String teamName;
    private final TeamConfig teamConfig;
    private final BronzeGenerator bronzeGenerator;
    private final IronGenerator ironGenerator;
    private final GoldGenerator goldGenerator;

    protected Team(String teamIndex, String teamName, Color color) {
        this.color = color;
        this.teamIndex = teamIndex;
        this.teamName = teamName;
        this.teamConfig = GameConfig.teamInfo.get(teamIndex);
        this.bronzeGenerator = new BronzeGenerator(teamConfig.getBronze());
        this.ironGenerator = new IronGenerator(teamConfig.getIron());
        this.goldGenerator = new GoldGenerator(teamConfig.getGold());
        this.players = new LinkedList<>();
        this.spawnLocation = teamConfig.getSpawn();
        this.villager = new Merchant(teamConfig.getVillager());
        this.bed = new Bed(teamConfig.getBedFirst(), teamConfig.getBedSecond());

    }

    @Override
    public void spawn() {
        players.forEach(this::spawn);
    }

    @Override
    public void spawn(GamePlayer gamePlayer) {
        Player player = gamePlayer.getPlayer();
        Utils.resetPlayer(player);
        player.teleport(spawnLocation);
    }

    @Override
    public TeamConfig getTeamConfig() {
        return teamConfig;
    }

    @Override
    public BronzeGenerator getBronzeGenerator() {
        return bronzeGenerator;
    }

    @Override
    public GoldGenerator getGoldGenerator() {
        return goldGenerator;
    }

    @Override
    public IronGenerator getIronGenerator() {
        return ironGenerator;
    }

    @Override
    public void addPlayer(GamePlayer player) {
        players.add(player);
    }

    @Override
    public void removePlayer(GamePlayer player) {
        players.remove(player);
    }

    public org.bukkit.Color getColor() {
        return color;
    }

    public LinkedList<GamePlayer> getPlayers() {
        return players;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public Entity getVillager() {
        return villager;
    }

    public Bed getBed() {
        return bed;
    }

    public String getTeamIndex() {
        return teamIndex;
    }

    public String getTeamName() {
        return teamName;
    }

}
