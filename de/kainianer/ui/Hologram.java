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
package de.kainianer.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.server.v1_7_R4.EntityHorse;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.EntityWitherSkull;
import net.minecraft.server.v1_7_R4.PacketPlayOutAttachEntity;
import net.minecraft.server.v1_7_R4.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_7_R4.PacketPlayOutSpawnEntity;
import net.minecraft.server.v1_7_R4.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_7_R4.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author kainianer
 */
public class Hologram {

    private static final double distance = 0.23;
    private List<String> lines = new ArrayList<>();
    private final List<Integer> ids = new ArrayList<>();
    private boolean showing = false;
    private Location location;
    private float dy;

    public Hologram(String... lines) {
        this.lines.addAll(Arrays.asList(lines));
    }

    public Hologram(float dy, String... lines) {
        this.lines.addAll(Arrays.asList(lines));
        this.dy = dy;
    }

    public void change(String... lines) {
        destroy();
        this.lines = Arrays.asList(lines);
        show(this.location);
    }

    public void addDy(float dy) {
        this.dy = dy;
    }

    public void show(Location loc) {
        if (showing == true) {
            return;
        }
        Location first = loc.clone().add(0, (this.lines.size() / 2) * distance, 0);
        for (int i = 0; i < this.lines.size(); i++) {
            ids.addAll(showLine(first.clone(), this.dy, this.lines.get(i)));
            first.subtract(0, distance, 0);
        }
        showing = true;
        this.location = loc;
    }

    public void show(Location loc, long ticks) {
        show(loc);
        new BukkitRunnable() {
            @Override
            public void run() {
                destroy();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("GenuineMMO"), ticks);
    }

    public void destroy() {
        if (showing == false) {
            return;
        }
        int[] ints = new int[this.ids.size()];
        for (int j = 0; j < ints.length; j++) {
            ints[j] = ids.get(j);
        }
        PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(ints);
        for (Player player : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        }
        showing = false;
        this.location = null;
    }

    private static List<Integer> showLine(Location loc, float dy, String text) {
        WorldServer world = ((CraftWorld) loc.getWorld()).getHandle();
        EntityWitherSkull skull = new EntityWitherSkull(world);
        skull.setLocation(loc.getX(), loc.getY() + 1 + 55 + dy, loc.getZ(), 0, 0);
        PacketPlayOutSpawnEntity skull_packet = new PacketPlayOutSpawnEntity(skull, 1);

        EntityHorse horse = new EntityHorse(world);
        horse.setLocation(loc.getX(), loc.getY() + 55 + dy, loc.getZ(), 0, 0);
        horse.setAge(-1700000);
        horse.setCustomName(text);
        horse.setCustomNameVisible(true);
        PacketPlayOutSpawnEntityLiving packedt = new PacketPlayOutSpawnEntityLiving(horse);
        for (Player player : loc.getWorld().getPlayers()) {
            EntityPlayer nmsPlayer = ((CraftPlayer) player).getHandle();
            nmsPlayer.playerConnection.sendPacket(packedt);
            nmsPlayer.playerConnection.sendPacket(skull_packet);

            PacketPlayOutAttachEntity pa = new PacketPlayOutAttachEntity(0, horse, skull);
            nmsPlayer.playerConnection.sendPacket(pa);
        }
        return Arrays.asList(skull.getId(), horse.getId());
    }

}
