/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kainianer.ui;

import de.kainianer.genuine.Main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.confuser.barapi.BarAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 *
 * @author kainianer
 */
public class TargetBars {

    private final Map<LivingEntity, List<Player>> map = new HashMap<>();
    private final Map<LivingEntity, Long> toRemove = new HashMap<>();

    public static void updateForEntity(LivingEntity ent, double damage) {
        TargetBars.getInstance().updateBarsForEntity(ent, damage);
    }

    public static TargetBars getInstance() {
        return ((Main) Bukkit.getPluginManager().getPlugin("GenuineMMO")).getTargetBars();
    }

    public LivingEntity getTargetOfPlayer(Player player) {
        for (Map.Entry<LivingEntity, List<Player>> entry : this.map.entrySet()) {
            if (entry.getValue().contains(player)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public List<Player> getPlayersTargettingEntity(LivingEntity entity) {
        if (this.map.get(entity) == null) {
            return new ArrayList<>();
        } else {
            return this.map.get(entity);
        }
    }

    public void updateBarsForEntity(LivingEntity entity, double damage) {
        float percent = ((float) entity.getHealth() - (float) (damage)) / ((float) (((Damageable) entity).getMaxHealth()));
        String name = entity instanceof Player ? ((Player) entity).getName() : entity.getCustomName();
        for (Player p : this.getPlayersTargettingEntity(entity)) {
            if (percent > 0) {
                BarAPI.setMessage(p, name, percent * 100);
            } else {
                BarAPI.setMessage(p, name, 0f);
                this.toRemove.put(entity, System.currentTimeMillis() + 20);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getServer().getPluginManager().getPlugin("GenuineMMO"), new Runnable() {
                    @Override
                    public void run() {
                        ((Main) Bukkit.getServer().getPluginManager().getPlugin("GenuineMMO")).getTargetBars().delayedRemove();
                    }
                }, 20L);
            }
        }
    }

    public void removeTargetFromAllPlayers(LivingEntity entity) {
        for (Player p : this.getPlayersTargettingEntity(entity)) {
            BarAPI.removeBar(p);
        }
        this.map.remove(entity);
    }

    public void setTargetOfPlayer(Player player, LivingEntity target) {
        if (this.map.containsKey(target)) {
            this.map.get(target).add(player);
        } else {
            List<Player> list = new ArrayList<>();
            list.add(player);
            this.map.put(target, list);
            System.out.println("Set target of player " + player.getName());
        }
    }

    @SuppressWarnings("empty-statement")
    public void removePlayerOfTarget(Player player) {
        for (Map.Entry<LivingEntity, List<Player>> entry : this.map.entrySet()) {
            if (entry.getValue().contains(player)) {
                entry.getValue().remove(player);
            }
        }
    }

    public void removePlayersOfTarget(List<Player> playerList) {
        for (Player p : playerList) {
            this.removePlayerOfTarget(p);
        }
    }

    public void delayedRemove() {
        for (Map.Entry<LivingEntity, Long> entry : this.toRemove.entrySet()) {
            long l = entry.getValue();
            if (l <= System.currentTimeMillis()) {
                this.removeTargetFromAllPlayers(entry.getKey());
            }
        }
    }

    public boolean hasPlayerTarget(Player player) {
        return this.getTargetOfPlayer(player) != null;
    }
}
