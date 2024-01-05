package com.bedwars.game.nms.scoreboard;

import com.bedwars.BedWars;
import com.bedwars.game.Game;
import com.bedwars.game.nms.NMS;
import com.bedwars.game.player.GamePlayer;
import com.bedwars.game.player.IPlayerProvider;
import com.bedwars.game.utils.Utils;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.entity.Player;

public class PacketScoreboard implements NMS {

    @Override
    public void send(Player player) {
        Game map = BedWars.getInstance().getGame();

        GamePlayer info = IPlayerProvider.getGamePlayer(player);

        Scoreboard scoreboard = new Scoreboard();
        ScoreboardObjective objective = scoreboard.registerObjective(Utils.color("&b&lBedWars"), IScoreboardCriteria.b);

        PacketPlayOutScoreboardObjective removePacket = new PacketPlayOutScoreboardObjective(objective,1);
        PacketPlayOutScoreboardObjective createPacket = new PacketPlayOutScoreboardObjective(objective,0);
        PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1,objective);

        Utils.sendPacket(player,removePacket);
        Utils.sendPacket(player,createPacket);
        Utils.sendPacket(player,display);

        addLine(scoreboard,objective,"&fКарта &l" + map.getMapName(),player,8);
        addLine(scoreboard,objective,"&e",player,7);
        addLine(scoreboard,objective,"&c" + map.getRedTeam().getBed().toStringAlive() + " Красные &7(" + map.getRedTeam().getPlayers().size() + "/1)",player,6);
        addLine(scoreboard,objective,"&9"  + map.getBlueTeam().getBed().toStringAlive() + " Синие &7(" + map.getBlueTeam().getPlayers().size() + "/1)",player,5);
        addLine(scoreboard,objective,"&3",player,4);
        addLine(scoreboard,objective,"&fУбийства &e" + info.getKills(),player,3);
        addLine(scoreboard,objective,"&fСмертей &e" + info.getDeaths(),player,2);
        addLine(scoreboard,objective,"&6",player,1);
        addLine(scoreboard,objective,"&a&lVimeWorld",player,0);
    }

    public void addLine(Scoreboard scoreboard, ScoreboardObjective objective,String text,Player player,int id) {
        ScoreboardScore scoreboardScore = new ScoreboardScore(scoreboard,objective,Utils.color(text));
        scoreboardScore.setScore(id);

        PacketPlayOutScoreboardScore packetPlayOutScoreboardScore = new PacketPlayOutScoreboardScore(scoreboardScore);

        Utils.sendPacket(player,packetPlayOutScoreboardScore);
    }
}
