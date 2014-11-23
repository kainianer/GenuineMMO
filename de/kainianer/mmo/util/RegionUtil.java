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

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

/**
 *
 * @author kainianer
 */
public class RegionUtil {

    public static List<LivingEntity> getEntitiesInRegion(ProtectedRegion region, World world) {
        List<LivingEntity> entityList = new ArrayList<>();
        for (Entity entity : world.getEntities()) {
            if (entity instanceof LivingEntity) {
                if (region.contains((int) entity.getLocation().getX(), (int) entity.getLocation().getY(), (int) entity.getLocation().getZ())) {
                    entityList.add((LivingEntity) entity);
                }
            }
        }
        return entityList;
    }

    public static Location getRandomLocationInRegion(ProtectedRegion region, World world) {
        BlockVector vec = region.getMaximumPoint();
        BlockVector vecc = region.getMinimumPoint();
        Random ran = new Random();
        int randomX = ran.nextInt(Math.abs((int) (vec.getX() - vecc.getX())));
        int randomZ = ran.nextInt(Math.abs((int) (vec.getZ() - vecc.getZ())));
        int x = (int) (vec.getX() - randomX);
        int z = (int) (vecc.getZ() + randomZ);
        int y = world.getHighestBlockYAt(x, z);

        return new Location(world, x, y, z);
    }

    public static int getRegionArea(ProtectedRegion region) {
        int length = (int)Math.abs(region.getMaximumPoint().getX() - region.getMinimumPoint().getX());
        int width = (int)Math.abs(region.getMaximumPoint().getZ() - region.getMinimumPoint().getZ());
        return length * width;
    }

}
