package com.bedwars.game.team.merchant;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class EntityListener implements Listener {

    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity clickedEntity = event.getRightClicked();

        if (clickedEntity != null) {
            com.bedwars.game.team.merchant.Entity entityManager = com.bedwars.game.team.merchant.Entity.getEntity(clickedEntity.getCustomName());

            if (entityManager != null) {
                entityManager.onInteract(player);
                event.setCancelled(true);
            }
        }
    }
}