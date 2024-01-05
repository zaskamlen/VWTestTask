package com.bedwars.game.team.menu;

import com.bedwars.BedWars;
import com.bedwars.game.Game;
import com.bedwars.game.item.ItemBuilder;
import com.bedwars.game.menu.Menu;
import com.bedwars.game.nms.scoreboard.PacketScoreboard;
import com.bedwars.game.player.GamePlayer;
import com.bedwars.game.player.IPlayerProvider;
import com.bedwars.game.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TeamSelectMenu extends Menu {
    private final ItemStack teamRed = new ItemBuilder(Material.WOOL, 14).setName("&cКрасная команда").setLore("&aВыбрать команду").build();
    private final ItemStack teamBlue = new ItemBuilder(Material.WOOL, 11).setName("&9Синяя команда").setLore("&aВыбрать команду").build();
    public TeamSelectMenu() {
        super("Выбор команды", 27);

        setItem(12, teamRed);
        setItem(14, teamBlue);
    }

    @Override
    public void onClick(int slot, ItemStack item, Player player) {
        PacketScoreboard scoreboard = new PacketScoreboard();
        Game game = BedWars.getInstance().getGame();
        GamePlayer gamePlayer = IPlayerProvider.getGamePlayer(player);
        if (slot == 12 && item.equals(teamRed)) {
            if (game.getRedTeam().getPlayers().contains(gamePlayer)) return;

            player.sendMessage(Utils.color("Вы зашли за команду &cКрасные"));

            gamePlayer.setTeam(game.getRedTeam());

            game.getRedTeam().addPlayer(gamePlayer);
            game.getBlueTeam().removePlayer(gamePlayer);

            scoreboard.send(player);
            player.closeInventory();
        }else if (slot == 14 && item.equals(teamBlue)) {
            if (game.getBlueTeam().getPlayers().contains(gamePlayer)) return;

            player.sendMessage(Utils.color("Вы зашли за команду &9Синие"));

            gamePlayer.setTeam(game.getBlueTeam());

            game.getBlueTeam().addPlayer(gamePlayer);
            game.getRedTeam().removePlayer(gamePlayer);

            scoreboard.send(player);
            player.closeInventory();
        }
    }
}
