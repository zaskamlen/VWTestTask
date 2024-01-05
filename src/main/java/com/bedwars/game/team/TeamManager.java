package com.bedwars.game.team;

import com.bedwars.game.config.TeamConfig;
import com.bedwars.game.generator.BronzeGenerator;
import com.bedwars.game.generator.GoldGenerator;
import com.bedwars.game.generator.IronGenerator;
import com.bedwars.game.player.GamePlayer;

public interface TeamManager {
    void spawn();
    void spawn(GamePlayer player);
    void addPlayer(GamePlayer player);
    void removePlayer(GamePlayer player);
    TeamConfig getTeamConfig();
    BronzeGenerator getBronzeGenerator();
    IronGenerator getIronGenerator();
    GoldGenerator getGoldGenerator();
}
