package com.bedwars.game.player.stats;

import java.util.HashMap;
import java.util.Map;

public interface NBTManager {

    void write(String key, Object value);
    void set(String key, Object value);
    Object get(String key);
    String getString(String key);
    int getInt(String key);
    double getDouble(String key);
    long getLong(String key);
    boolean getBoolean(String key);


}
