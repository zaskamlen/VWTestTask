package com.bedwars.game.player.stats;

import java.util.HashMap;
import java.util.Map;

public class NBT implements NBTManager{

    private final Map<String,Object> NBT_MAP = new HashMap<>();

    @Override
    public void write(String key, Object value) {
        NBT_MAP.put(key,value);
    }

    @Override
    public void set(String key, Object value) {
        NBT_MAP.remove(key);
        NBT_MAP.put(key,value);
    }

    @Override
    public Object get(String key) {
        return NBT_MAP.get(key);
    }

    @Override
    public String getString(String key) {
        Object object = get(key);

        if (object instanceof String) {
            return (String) object;
        }else {
            return "";
        }
    }

    @Override
    public int getInt(String key) {
        Object object = get(key);

        if (object instanceof Integer) {
            return (int) object;
        }else {
            return 0;
        }
    }

    @Override
    public double getDouble(String key) {
        Object object = get(key);

        if (object instanceof Double) {
            return (double) object;
        }else {
            return 0.0;
        }
    }


    @Override
    public long getLong(String key) {
        Object object = get(key);

        if (object instanceof Long) {
            return (long) object;
        }else {
            return 0L;
        }
    }

    @Override
    public boolean getBoolean(String key) {
        Object object = get(key);

        if (object instanceof Boolean) {
            return (boolean) object;
        }else {
            return false;
        }
    }
}
