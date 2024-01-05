package com.bedwars.game.command;

import com.bedwars.BedWars;
import com.bedwars.game.Game;
import com.bedwars.game.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if (commandSender instanceof Player){
            Player player = (Player) commandSender;
            Game game = BedWars.getInstance().getGame();

            if (args.length == 0) {
                player.sendMessage(Utils.color(
                        "&eГлавные команды",
                        "",
                        "&7/" + label + " &astart",
                        "&7/" + label + " &astop",
                        ""
                ));
            }else if (args[0].equalsIgnoreCase("start")) {
                game.start();
            }else if (args[0].equalsIgnoreCase("stop")) {
                game.stop();
            }

        }

        return true;
    }
}
