package com.bedwars.game.player;

import com.bedwars.BedWars;
import com.bedwars.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public interface IPlayerProvider {

    static GamePlayer getGamePlayer(Player player) {
        Game game = BedWars.getInstance().getGame();
        return game.getPlayers().get(player);
    }
    static GamePlayer getGamePlayer(String player) {
        Game game = BedWars.getInstance().getGame();
        return game.getPlayers().get(Bukkit.getPlayer(player));
    }

}
