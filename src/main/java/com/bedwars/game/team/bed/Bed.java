package com.bedwars.game.team.bed;

import org.bukkit.Location;

public class Bed {
    private final Location first;
    private final Location second;
    private  boolean alive;

    public Bed(Location first, Location second) {
        this.first = first;
        this.second = second;
        this.alive = true;
    }

    public Location getFirst() {
        return first;
    }

    public Location getSecond() {
        return second;
    }
    public String toStringAlive() {
        return alive ? "✔" : "✘";
    }
    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
