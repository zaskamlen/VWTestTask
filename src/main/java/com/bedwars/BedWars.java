package com.bedwars;

import com.bedwars.game.Game;
import com.bedwars.game.command.GameCommand;
import com.bedwars.game.config.GameConfig;
import com.bedwars.game.listener.bed.BedListener;
import com.bedwars.game.listener.block.BlockListener;
import com.bedwars.game.listener.game.GameListener;
import com.bedwars.game.listener.player.PlayerListener;
import com.bedwars.game.listener.team.TeamListener;
import com.bedwars.game.menu.MenuListener;
import com.bedwars.game.nms.scoreboard.PacketScoreboard;
import com.bedwars.game.team.merchant.EntityListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class BedWars extends JavaPlugin {
    private static BedWars instance;
    private Game game;
    private GameConfig gameConfig;
    private PacketScoreboard scoreboard;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        gameConfig = new GameConfig();
        game = new Game();
        scoreboard = new PacketScoreboard();

        Bukkit.getPluginManager().registerEvents(new BedListener(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(),this);
        Bukkit.getPluginManager().registerEvents(new BlockListener(),this);
        Bukkit.getPluginManager().registerEvents(new GameListener(),this);
        Bukkit.getPluginManager().registerEvents(new TeamListener(),this);
        Bukkit.getPluginManager().registerEvents(new MenuListener(),this);
        Bukkit.getPluginManager().registerEvents(new EntityListener(),this);

        getCommand("bedwars").setExecutor(new GameCommand());
    }

    @Override
    public void onDisable() {
        game.reset();
    }

    public GameConfig getGameConfig() {
        return gameConfig;
    }

    public static BedWars getInstance() {
        return instance;
    }

    public PacketScoreboard getScoreboard() {
        return scoreboard;
    }

    public Game getGame() {
        return game;
    }
}
