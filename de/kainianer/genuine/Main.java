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

import de.kainianer.genuine.effects.CustomEffect;
import de.kainianer.genuine.commands.TestCommand;
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
import de.kainianer.genuine.item.CustomItem;
import de.kainianer.genuine.region.RegionManager;
import de.kainianer.genuine.spell.SpellManager;
import de.kainianer.genuine.util.TargetBarManager;
import de.kainianer.genuine.util.HungerManager;
import de.kainianer.genuine.util.ItemLoader;
import de.slikey.effectlib.EffectLib;
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

    private final TargetBarManager targetBarManger;
    private final SpellManager spellManager;
    private final Map<Item, CustomEffect> legendaryOnGroundList = new HashMap<>();
    private final Map<InventoryHolder, Inventory> quiverInventories = new HashMap<>();
    private final Map<UUID, WitherSkull> effectPassengers = new HashMap<>();
    private final Map<String, CustomItem> itemList = new HashMap<>();
    private final HungerManager hungerManager;
    private final RegionManager regionManager;

    public Main() {
        this.targetBarManger = new TargetBarManager(this);
        this.spellManager = new SpellManager(this);
        this.hungerManager = new HungerManager(this);
        this.regionManager = new RegionManager();
    }

    @Override
    public void onEnable() {

        //loading events
        System.out.println("[GenuineMMO] Loading events ...");
        PluginManager pm = this.getServer().getPluginManager();
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
        System.out.println("[GenuineMMO] Events loaded");

        //loading items
        System.out.println("[GenuineMMO] Loading Items ...");
        ItemLoader loader = new ItemLoader();
        for (CustomItem item : loader.getItems()) {
            this.itemList.put(item.getItemMeta().getDisplayName(), item);
        }
        System.out.println("[GenuineMMO] Items loaded");

        //adding commmands
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
        System.out.println("[GenuineMMO] Loading managers ...");
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, this.spellManager, 0, 20);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, this.hungerManager, 0, 40);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, this.regionManager, 0, 200);
        System.out.println("[GenuineMMO] Managers loaded");

        System.out.println("[GenuineMMO] Successfully enabled");
    }

    @Override
    public void onDisable() {
        System.out.println("[GenuineMMO] Successfully disabled!");
    }

    public TargetBarManager getTargetBars() {
        return this.targetBarManger;
    }

    public Map<InventoryHolder, Inventory> getQuiverInventories() {
        return this.quiverInventories;
    }

    public Map<Item, CustomEffect> getLegendaryOnGroundList() {
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

    public Map<String, CustomItem> getItemList() {
        return this.itemList;
    }

    public SpellManager getSpellManager() {
        return this.spellManager;
    }

}
