/* 
 * The MIT License
 *
 * Copyright 2014 kainianer.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package de.kainianer.genuine.util;

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
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author kainianer
 */
public class TargetBarManager {

    private final Map<LivingEntity, List<Player>> map = new HashMap<>();
    private final Map<LivingEntity, Long> toRemove = new HashMap<>();
    private final JavaPlugin plugin;

    public TargetBarManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public static void updateForEntity(LivingEntity ent, double damage) {
        TargetBarManager.getInstance().updateBarsForEntity(ent, damage);
    }

    public static TargetBarManager getInstance() {
        return Main.getInstance().getTargetBars();
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
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
                    @Override
                    public void run() {
                        Main.getInstance().getTargetBars().delayedRemove();
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
        }
    }

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
