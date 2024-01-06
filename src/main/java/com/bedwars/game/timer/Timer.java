package com.bedwars.game.timer;

import com.bedwars.BedWars;
import com.bedwars.game.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer extends BukkitRunnable implements TimerManager {

    private int secondsLeft;
    private final Player player;
    private final Runnable onStop;

    public Timer(Player player, int initialSeconds, Runnable onStop) {
        this.secondsLeft = initialSeconds;
        this.player = player;
        this.onStop = onStop;
    }

    @Override
    public void run() {
        if (secondsLeft <= 0) {
            stop();
            return;
        }

        int hours = secondsLeft / 3600;
        int minutes = (secondsLeft % 3600) / 60;
        int seconds = secondsLeft % 60;

        Utils.sendActionBar(player,"&fДо конца игры &e" +  String.format("%02d:%02d:%02d", hours, minutes, seconds));

        secondsLeft--;
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }

    public void setTime(int seconds) {
        this.secondsLeft = seconds;
    }

    private void sendActionBar(String message) {
        System.out.println(message);
    }

    @Override
    public void start() {
        runTaskTimer(BedWars.getInstance(), 0, 20);
    }

    @Override
    public void stop() {
        onStop.run();
        cancel();
    }
}
