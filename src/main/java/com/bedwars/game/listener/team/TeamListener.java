package com.bedwars.game.listener.team;

import com.bedwars.BedWars;
import com.bedwars.game.Game;
import com.bedwars.game.GameState;
import com.bedwars.game.team.menu.TeamSelectMenu;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class TeamListener implements Listener {

    @EventHandler
    public void onClickItem(PlayerInteractEvent event) {
        Game map = BedWars.getInstance().getGame();
        TeamSelectMenu menu = new TeamSelectMenu();

        switch (event.getAction()) {
            case RIGHT_CLICK_AIR:
            case RIGHT_CLICK_BLOCK:

                if (map.getGameState() == GameState.WAITING) {
                    if (event.getItem() != null && event.getItem().getType() == Material.NAME_TAG) {
                        menu.open(event.getPlayer());
                    }
                }
                break;
        }
    }


}
