package com.bedwars.game;

import com.bedwars.BedWars;
import com.bedwars.game.config.GameConfig;
import com.bedwars.game.config.TeamConfig;
import com.bedwars.game.generator.BronzeGenerator;
import com.bedwars.game.mysql.PlayerDatabase;
import com.bedwars.game.player.GamePlayer;
import com.bedwars.game.team.BlueTeam;
import com.bedwars.game.team.RedTeam;
import com.bedwars.game.team.Team;
import com.bedwars.game.timer.Timer;
import com.bedwars.game.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import javax.rmi.CORBA.Util;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Game implements GameManager {

    private GameState gameState = GameState.WAITING;
    private final String mapName = BedWars.getInstance().getGameConfig().getMapName();
    private final Location spawn = BedWars.getInstance().getGameConfig().getSpawn();
    private final RedTeam redTeam;
    private final BlueTeam blueTeam;
    private final PlayerDatabase database;
    private final Map<Player, GamePlayer> players = new ConcurrentHashMap<>();
    private final Set<Block> blocks = new HashSet<>();

    public Game() {
        GameConfig gameConfig = BedWars.getInstance().getGameConfig();

        this.database = new PlayerDatabase(gameConfig.getHost(), gameConfig.getUsername(),gameConfig.getPassword());
        this.redTeam = new RedTeam();
        this.blueTeam = new BlueTeam();
    }

    @Override
    public void start() {
        setGameState(GameState.GAME);

        redTeam.spawn();
        redTeam.getBronzeGenerator().spawn();
        redTeam.getIronGenerator().spawn();
        redTeam.getGoldGenerator().spawn();

        blueTeam.spawn();
        blueTeam.getBronzeGenerator().spawn();
        blueTeam.getIronGenerator().spawn();
        blueTeam.getGoldGenerator().spawn();

        Bukkit.getScheduler().runTaskLater(BedWars.getInstance(), () -> {
            redTeam.getVillager().spawn();
            blueTeam.getVillager().spawn();
        },10);

        players.values().forEach(player -> {
            Timer timer = new Timer(player.getPlayer(), 1800, this::stop);
            timer.start();
        });


    }

    @Override
    public void stop() {
        setGameState(GameState.END);
        Team winner = getWinnerTeam();
        players.values().forEach(player -> {
            if (winner != null) {
                player.getPlayer().sendMessage(Utils.color("&7Победила команда: &e" + winner.getTeamName()));
            }else {
                player.getPlayer().sendMessage(Utils.color("&7Победила команда: &eНичья"));
            }
            database.save(player);
        });
        Bukkit.getScheduler().runTaskLater(BedWars.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                reset();
                Bukkit.shutdown();
            }
        },100);
    }

    @Override
    public void addPlayer(GamePlayer player) {
        players.put(player.getPlayer(),player);
    }

    @Override
    public void removePlayer(GamePlayer player) {
        players.remove(player.getPlayer());
    }

    @Override
    public void reset() {
        blocks.forEach(block -> block.setType(Material.AIR));

        Bukkit.getWorld("world").getEntities().forEach(entity -> {
            switch (entity.getType()) {
                case PLAYER:
                case ITEM_FRAME:
                case ARMOR_STAND:
                    return;
                default:
                    entity.remove();
                    break;
            }
        });

    }

    @Override
    public Team getWinnerTeam() {
        List<Team> teams = new ArrayList<>();
        teams.add(redTeam);
        teams.add(blueTeam);

        Team winner = null;
        int maxPlayers = 0;

        for (Team team : teams) {
            int teamSize = team.getPlayers().size();

            if (teamSize > maxPlayers || (teamSize == maxPlayers && team.getBed().isAlive() && !winner.getBed().isAlive())) {
                winner = team;
                maxPlayers = teamSize;
            }
        }

        return winner;

        // Победителей нет, это ничья
    }
    public RedTeam getRedTeam() {
        return redTeam;
    }

    public BlueTeam getBlueTeam() {
        return blueTeam;
    }

    public Location getSpawn() {
        return spawn;
    }

    public String getMapName() {
        return mapName;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Map<Player, GamePlayer> getPlayers() {
        return players;
    }

    public Set<Block> getBlocks() {
        return blocks;
    }

    public PlayerDatabase getDatabase() {
        return database;
    }

    public GameState getGameState() {
        return gameState;
    }
}
