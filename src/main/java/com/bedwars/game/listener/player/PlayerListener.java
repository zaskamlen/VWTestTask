package com.bedwars.game.listener.player;

import com.bedwars.BedWars;
import com.bedwars.game.Game;
import com.bedwars.game.GameLobbyItems;
import com.bedwars.game.GameState;
import com.bedwars.game.nms.scoreboard.PacketScoreboard;
import com.bedwars.game.player.GamePlayer;
import com.bedwars.game.utils.Utils;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerListener implements Listener {
    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Game map = BedWars.getInstance().getGame();
        if (map.getGameState() != GameState.WAITING) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Ошибка, сервер не может принять вас");
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Game map = BedWars.getInstance().getGame();

        if (map.getGameState() == GameState.WAITING || map.getGameState() == GameState.END) {
            if (event.getCause() == EntityDamageEvent.DamageCause.VOID) {
                event.getEntity().teleport(map.getSpawn());
            }
            event.setCancelled(true);
        } else {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Game map = BedWars.getInstance().getGame();
        Player player = event.getPlayer();

        if (map.getGameState() == GameState.WAITING) {
            PacketScoreboard scoreboard = new PacketScoreboard();
            GamePlayer gamePlayer = new GamePlayer(player);
            map.addPlayer(gamePlayer);

            player.teleport(map.getSpawn());
            player.setCanPickupItems(true);

            Utils.resetPlayer(player);

            scoreboard.send(player);

            GameLobbyItems.give(player);

            event.setJoinMessage(Utils.color("&fИгрок &e" + gamePlayer.getName() + "&f подключился"));
        } else {
            event.setJoinMessage(null);
        }
    }
    @EventHandler
    public void onCraft(CraftItemEvent event) {
        event.setCancelled(true);
    }
    @EventHandler
    public void onManipulateArmorStand(PlayerArmorStandManipulateEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDamageArmorStand(EntityDamageEvent event) {
        if (event.getEntity().getType() == EntityType.ARMOR_STAND) {
            event.setCancelled(true);
        }
    }

}
