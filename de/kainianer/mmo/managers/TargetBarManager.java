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
package de.kainianer.mmo.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.confuser.barapi.BarAPI;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 *
 * @author kainianer
 */
public class TargetBarManager implements Runnable {

    public static TargetBarManager instance = new TargetBarManager();
    private final Map<LivingEntity, List<Player>> map = new HashMap<>();
    private final Map<LivingEntity, Long> toRemove = new HashMap<>();

    public static void updateForEntity(LivingEntity ent, double damage) {
        TargetBarManager.getInstance().updateBarsForEntity(ent, damage);
    }

    public static TargetBarManager getInstance() {
        return instance;
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
        float percent = ((float) entity.getHealth() - (float) (damage)) / ((float) (entity.getMaxHealth()));
        String name = entity instanceof Player ? ((Player) entity).getName() : entity.getCustomName();
        for (Player p : this.getPlayersTargettingEntity(entity)) {
            if (percent <= 0) {
                BarAPI.setMessage(p, name, 1f);
                if (!this.toRemove.containsKey(entity)) {
                    this.toRemove.put(entity, System.currentTimeMillis() + 1000);
                }
            } else {
                BarAPI.setMessage(p, name, percent * 100);
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
            List<Player> players = this.getPlayersTargettingEntity(target);
            for (Player p : players) {
                if (p.getUniqueId().equals(player.getUniqueId())) {
                    return;
                }
            }
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

    public boolean hasPlayerTarget(Player player) {
        return this.getTargetOfPlayer(player) != null;
    }

    @Override
    public void run() {
        for (Map.Entry<LivingEntity, Long> entry : this.toRemove.entrySet()) {
            long l = entry.getValue();
            if (l <= System.currentTimeMillis()) {
                this.removeTargetFromAllPlayers(entry.getKey());
            }
        }
    }
}
