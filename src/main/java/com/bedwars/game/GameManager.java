package com.bedwars.game;

import com.bedwars.game.player.GamePlayer;
import com.bedwars.game.team.Team;
import org.bukkit.entity.Player;

public interface GameManager {
    void start();
    void stop();
    void addPlayer(GamePlayer player);
    void removePlayer(GamePlayer player);
    void reset();
    Team getWinnerTeam();
}
