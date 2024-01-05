package com.bedwars.game.listener.game;

import com.bedwars.BedWars;
import com.bedwars.game.Game;
import com.bedwars.game.GameState;
import com.bedwars.game.nms.scoreboard.PacketScoreboard;
import com.bedwars.game.player.GamePlayer;
import com.bedwars.game.player.IPlayerProvider;
import com.bedwars.game.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class GameListener implements Listener {

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        event.setCancelled(BedWars.getInstance().getGame().getGameState() != GameState.GAME);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Game map = BedWars.getInstance().getGame();
        PacketScoreboard scoreboard = new PacketScoreboard();
        GamePlayer info = IPlayerProvider.getGamePlayer(event.getPlayer());

        switch (map.getGameState()) {
            case WAITING:
            case END:
                event.setRespawnLocation(map.getSpawn());
                break;
            case GAME:

                if (info.getTeam().getBed().isAlive()) {
                    info.getTeam().spawn(info);
                    event.setRespawnLocation(info.getTeam().getSpawnLocation());
                    scoreboard.send(info.getPlayer());
                }else {

                    Player player = info.getPlayer();
                    player.setAllowFlight(true);

                    Bukkit.getScheduler().runTaskLater(BedWars.getInstance(), new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,Integer.MAX_VALUE,1));
                        }
                    },20);

                    player.sendMessage(Utils.color("&aВы зритель"));

                    // TODO Если надо добавить выбор для зрителя

                    event.setRespawnLocation(map.getSpawn());

                    Utils.resetPlayer(player);

                    info.getTeam().removePlayer(info);

                    Bukkit.getOnlinePlayers().forEach(gamePlayer -> {
                        gamePlayer.hidePlayer(player);
                        scoreboard.send(gamePlayer);
                    });
                }

                break;
        }

    }

    @EventHandler
    public void onDamageTeamPlayer(EntityDamageByEntityEvent event) {
        Game map = BedWars.getInstance().getGame();

        if (map.getGameState() == GameState.WAITING || map.getGameState() == GameState.END) {
            event.setCancelled(true);
            return;
        }

        if (event.getDamager().getType() == EntityType.PLAYER && event.getEntity().getType() == EntityType.PLAYER) {
            Player player = (Player) event.getEntity();
            Player damager = (Player) event.getDamager();

            GamePlayer playerInfo = IPlayerProvider.getGamePlayer(player);
            GamePlayer damagerInfo = IPlayerProvider.getGamePlayer(damager);

            event.setCancelled(damagerInfo.getTeam() != null && damagerInfo.getTeam().getPlayers().contains(playerInfo));
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        PacketScoreboard scoreboard = new PacketScoreboard();
        Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();
        Game map = BedWars.getInstance().getGame();

        switch (map.getGameState()) {
            case WAITING:
            case END:
                event.setDeathMessage(null);
                break;
            case GAME:
                if (killer != null){
                    GamePlayer killerInfo = IPlayerProvider.getGamePlayer(killer);
                    GamePlayer playerInfo = IPlayerProvider.getGamePlayer(player);

                    killerInfo.addKills(1);
                    playerInfo.addDeaths(1);

                    event.setDeathMessage(Utils.color(
                            "&fИгрок &e" + killer.getPlayer().getDisplayName() + "&f убил игрока &e" + player.getPlayer().getDisplayName() + " &f и получил &a+40&e монет"));
                    scoreboard.send(playerInfo.getPlayer());
                    scoreboard.send(killerInfo.getPlayer());
                }else {
                    GamePlayer playerInfo = IPlayerProvider.getGamePlayer(player);
                    playerInfo.addDeaths(1);
                    event.setDeathMessage(Utils.color(
                            "&fИгрок &e" + player.getPlayer().getDisplayName() + "&f умер"));

                    scoreboard.send(player);
                }
                break;
        }


        event.setKeepInventory(true);
        event.setDroppedExp(0);
        event.setNewLevel(0);
        event.setNewTotalExp(0);
        event.setKeepLevel(true);

    }

}
