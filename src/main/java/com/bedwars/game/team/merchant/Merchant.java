package com.bedwars.game.team.merchant;

import com.bedwars.BedWars;
import com.bedwars.game.utils.Utils;
import net.minecraft.server.v1_8_R3.EntityVillager;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftVillager;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Merchant extends Entity{
    public Merchant(Location spawnLocation) {
        super(Utils.color("&e&lТорговец"),spawnLocation);
    }

    @Override
    public void spawn() {
        Location spawnLocation = getSpawnLocation();
        org.bukkit.entity.Villager villager = getSpawnLocation().getWorld().spawn(getSpawnLocation(), org.bukkit.entity.Villager.class);

        villager.setCustomName(Utils.color("&e&lТорговец"));
        villager.setCustomNameVisible(true);
        villager.setProfession(org.bukkit.entity.Villager.Profession.FARMER);
        villager.setNoDamageTicks(Integer.MAX_VALUE);
        villager.setRemainingAir(0);
        villager.setMaximumAir(0);

        Bukkit.getScheduler().runTaskTimer(BedWars.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {

                if (villager.getLocation().equals(spawnLocation)) return;
                villager.teleport(spawnLocation);
            }
        },20,20);

        EntityVillager nmsVillager = ((CraftVillager) villager).getHandle();
        nmsVillager.goalSelector = new PathfinderGoalSelector(nmsVillager.getWorld().methodProfiler);
        nmsVillager.targetSelector = new PathfinderGoalSelector(nmsVillager.getWorld().methodProfiler);
    }

    @Override
    public void onInteract(Player player) {
        MerchantShop shop = new MerchantShop();
        shop.open(player);
    }

}
