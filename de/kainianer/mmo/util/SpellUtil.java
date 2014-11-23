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
package de.kainianer.mmo.util;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 *
 * @author kainianer
 */
public class SpellUtil {

    public static Location getViewLocation(Player player) {
        return player.getTargetBlock(null, 100).getLocation();
    }

    public static Player getTargetPlayer(Player player) {
        return getTarget(player, player.getWorld().getPlayers());
    }

    public static Entity getTargetEntity(Entity entity) {
        return getTarget(entity, entity.getWorld().getEntities());
    }

    public static LivingEntity getTargetLivingEntity(Player player) {
        Player p = SpellUtil.getTargetPlayer(player);
        Entity ent = SpellUtil.getTargetEntity(player);
        if (ent != null && ent instanceof LivingEntity) {
            return (LivingEntity) ent;
        }
        if (p != null) {
            return p;
        }
        return null;
    }

    public static <T extends Entity> T getTarget(Entity entity, Iterable<T> entities) {
        T target = null;
        double threshold = 1;
        for (T other : entities) {
            Vector n = other.getLocation().toVector().subtract(entity.getLocation().toVector());
            if (entity.getLocation().getDirection().normalize().crossProduct(n).lengthSquared() < threshold && n.normalize().dot(entity.getLocation().getDirection().normalize()) >= 0) {
                if (target == null || target.getLocation().distanceSquared(entity.getLocation()) > other.getLocation().distanceSquared(entity.getLocation())) {
                    target = other;
                }
            }
        }
        return target;
    }

    public static List<Block> getBlocksInRadius(Location loc, int radius) {
        List<Block> blocks = new ArrayList<>();
        for (double x = loc.getX() - radius; x < loc.getX() + radius; x++) {
            for (double z = loc.getZ() - radius; z < loc.getZ() + radius; z++) {
                blocks.add(loc.getWorld().getBlockAt((int) x, (int) loc.getY(), (int) z));
            }

        }
        return blocks;
    }
}
