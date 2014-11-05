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
package de.kainianer.genuine;

import de.kainianer.genuine.commands.TestCommand;
import de.kainianer.genuine.events.onDamageByEntity;
import de.kainianer.genuine.events.onEntityDamage;
import de.kainianer.genuine.events.onEntityDeath;
import de.kainianer.genuine.events.onEntityTarget;
import de.kainianer.genuine.events.onEntityTargetLivingEntity;
import de.kainianer.genuine.events.onInventoryClick;
import de.kainianer.genuine.events.onInventoryClose;
import de.kainianer.genuine.events.onItemSpawn;
import de.kainianer.genuine.events.onItemUse;
import de.kainianer.genuine.events.onJoin;
import de.kainianer.genuine.events.onPlayerMove;
import de.kainianer.genuine.events.onPlayerPickupItem;
import de.kainianer.genuine.events.onBowShoot;
import de.kainianer.genuine.events.onEntityDamageByEntity;
import de.kainianer.ui.TargetBars;
import de.slikey.effectlib.EffectLib;
import de.slikey.effectlib.EffectManager;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private final TargetBars targetBars = new TargetBars();
    private final Map<Item, EffectManager> legendaryOnGroundList = new HashMap<>();
    private final Map<InventoryHolder, Inventory> quiverInventories = new HashMap<>();
    private final Map<UUID, WitherSkull> effectPassengers = new HashMap<>();
    
    @Override
    public void onEnable() {

        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new onDamageByEntity(), this);
        pm.registerEvents(new onEntityDamage(), this);
        pm.registerEvents(new onEntityDeath(), this);
        pm.registerEvents(new onJoin(), this);
        pm.registerEvents(new onPlayerMove(), this);
        pm.registerEvents(new onItemUse(), this);
        pm.registerEvents(new onEntityTargetLivingEntity(), this);
        pm.registerEvents(new onEntityTarget(), this);
        pm.registerEvents(new onItemSpawn(), this);
        pm.registerEvents(new onPlayerPickupItem(), this);
        pm.registerEvents(new onInventoryClose(), this);
        pm.registerEvents(new onInventoryClick(), this);
        pm.registerEvents(new onBowShoot(), this);
        pm.registerEvents(new onEntityDamageByEntity(), this);

        this.getCommand("test").setExecutor(new TestCommand());

        //updating names
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (World world : Bukkit.getServer().getWorlds()) {
                    for (Entity entity : world.getEntities()) {
                        if (!(entity instanceof Player) && entity instanceof LivingEntity) {
                            LivingEntity le = (LivingEntity) entity;
                            le.setCustomName(ChatColor.GRAY + ((Entity) le).getType().getName());
                            le.setCustomNameVisible(true);
                        }
                    }
                }
            }
        }, 0, 20L);

        System.out.println("[GenuineMMO] Successfully enabled");
    }

    @Override
    public void onDisable() {
        System.out.println("[GenuineMMO] Successfully disabled!");
    }

    public TargetBars getTargetBars() {
        return this.targetBars;
    }

    public Map<InventoryHolder, Inventory> getQuiverInventories() {
        return this.quiverInventories;
    }

    public Map<Item, EffectManager> getLegendaryOnGroundList() {
        return this.legendaryOnGroundList;
    }
    
    public Map<UUID, WitherSkull> getEffectPassengers() {
        return this.effectPassengers;
    }

    public static EffectLib getEffectLib() {
        return (EffectLib) Bukkit.getPluginManager().getPlugin("EffectLib");
    }

    public static Main getInstance() {
        return (Main) Bukkit.getPluginManager().getPlugin("GenuineMMO");
    }

}
