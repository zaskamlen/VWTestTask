package com.bedwars.game.listener.block;

import com.bedwars.BedWars;
import com.bedwars.game.Game;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class BlockListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Game map = BedWars.getInstance().getGame();

        switch (map.getGameState()) {

            case WAITING:
            case END:
                event.setCancelled(true);
                break;
            case GAME:
                if (event.getBlock().getType() == Material.WOOL) {
                    event.setCancelled(!map.getBlocks().contains(event.getBlock()));
                }else {
                    event.setCancelled(true);
                }
                break;

        }

    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Game map = BedWars.getInstance().getGame();

        switch (map.getGameState()) {

            case WAITING:
            case END:
                event.setCancelled(true);
                break;
            case GAME:
                if (event.getBlock().getType() == Material.WOOL) {
                    map.getBlocks().add(event.getBlock());
                    event.setCancelled(false);
                }else {
                    event.setCancelled(true);
                }
                break;

        }

    }

    @EventHandler
    public void onFormTo(BlockFromToEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPhysic(BlockPhysicsEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onForm(BlockFromToEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onExplodeBlock(BlockExplodeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onSpread(BlockSpreadEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPistonRetract(BlockPistonRetractEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPistonExtand(BlockPistonExtendEvent event) {
        event.setCancelled(true);
    }


}
