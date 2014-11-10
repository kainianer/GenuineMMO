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
package de.kainianer.genuine.region;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import de.kainianer.genuine.util.RegionUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.util.org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Zombie;

/**
 *
 * @author kainianer
 */
public class RegionManager implements Runnable {

    private Map<World, List<ProtectedRegion>> regions;
    private WorldGuardPlugin wp;
    private boolean init = false;

    public RegionManager() {
    }

    public void init() {
        this.wp = (WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
        this.regions = new HashMap<>();
        for (World world : Bukkit.getServer().getWorlds()) {
            if (world != null) {
                Map<String, ProtectedRegion> map = this.wp.getGlobalRegionManager().get(world).getRegions();
                List<ProtectedRegion> regionList = new ArrayList<>();
                for (Map.Entry<String, ProtectedRegion> entry : map.entrySet()) {
                    regionList.add(entry.getValue());
                }
                this.regions.put(world, regionList);
            } else {
                Validate.notNull(world);
            }
        }
        this.init = true;
    }

    @Override
    public void run() {
        if (!this.init) {
            this.init();
        }
        int spawned = 0;
        int max = 0;
        int are = 0;
        for (World world : this.regions.keySet()) {
            List<ProtectedRegion> list = this.regions.get(world);
            for (ProtectedRegion region : list) {
                System.out.println(region.getId());
                int size = RegionUtil.getEntitiesInRegion(region, world).size();
                int maxSize = (int)(RegionUtil.getRegionArea(region) * 0.025);
                max = maxSize;
                are = size;
                if (size < maxSize) {
                    for (int i = size; i < maxSize; i++) {
                        this.spawn(region, world);
                        spawned++;
                    }
                }
            }
        }
        System.out.println("Spawned " + spawned + " entities (" + are + "/" + max + ")");
    }

    private void spawn(ProtectedRegion region, World world) {
        world.spawn(RegionUtil.getRandomLocationInRegion(region, world), Zombie.class);
    }

}
