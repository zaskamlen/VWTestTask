package com.bedwars.game.listener.bed;

import com.bedwars.BedWars;
import com.bedwars.game.Game;
import com.bedwars.game.GameState;
import com.bedwars.game.nms.scoreboard.PacketScoreboard;
import com.bedwars.game.player.GamePlayer;
import com.bedwars.game.player.IPlayerProvider;
import com.bedwars.game.team.BlueTeam;
import com.bedwars.game.team.RedTeam;
import com.bedwars.game.team.Team;
import com.bedwars.game.team.bed.Bed;
import com.bedwars.game.utils.Utils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class BedListener implements Listener {

    @EventHandler
    public void onBedEnter(PlayerBedEnterEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Game map = BedWars.getInstance().getGame();
        GamePlayer playerInfo = IPlayerProvider.getGamePlayer(event.getPlayer());

        switch (map.getGameState()) {

            case WAITING:
            case END:
                event.setCancelled(true);
                break;
            case GAME:
                if (event.getBlock().getType() == Material.BED_BLOCK) {
                    breakBed(playerInfo, event.getBlock());
                    event.setCancelled(true);
                }
                break;

        }

    }

    private void breakBed(GamePlayer breaker,Block bed) {
        Team team = breaker.getTeam();
        Team breakTeam = getBreakTeam(breaker);
        PacketScoreboard scoreboard = new PacketScoreboard();

        Location p1 = team.getBed().getFirst();
        Location p2 = team.getBed().getSecond();


        int x = bed.getX();
        int y = bed.getY();
        int z = bed.getZ();

        Location bedLoc = new Location(bed.getWorld(),x,y,z);

        if (breakTeam.getBed().isAlive()) {
            if (bed.getType() == Material.BED_BLOCK) {
                if (p1.getBlock().getLocation().equals(bedLoc) || p2.getBlock().getLocation().equals(bedLoc)) {
                    breaker.getPlayer().sendMessage(Utils.color("&cВы ломаете свою кровать"));
                }else {
                    String teamName = breakTeam.getTeamName();

                    breakTeam.getBed().setAlive(false);
                    breaker.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED,80,2));
                    breaker.addBedBreaks(1);

                    Bukkit.getOnlinePlayers().forEach(player -> {
                        player.playSound(player.getLocation(),Sound.ENDERDRAGON_GROWL,100,1);
                        player.sendMessage(Utils.color("Игрок &e" + breaker.getName() + "&f сломал кровать команды &e" + teamName));
                        scoreboard.send(player);
                    });

                    breakTeam.getBed().getFirst().getBlock().setType(Material.AIR);
                    breakTeam.getBed().getSecond().getBlock().setType(Material.AIR);

                }
            }
        }

    }

    private Team getBreakTeam(GamePlayer playerInfo) {
        return playerInfo.getTeam().getColor() == Color.RED ? BedWars.getInstance().getGame().getBlueTeam() : BedWars.getInstance().getGame().getRedTeam();
    }



}
