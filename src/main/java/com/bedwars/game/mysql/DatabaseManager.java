package com.bedwars.game.mysql;

import com.bedwars.game.player.GamePlayer;

public interface DatabaseManager {
    void save(GamePlayer player);
    void connect(String url, String user, String password);
    void close();
}
