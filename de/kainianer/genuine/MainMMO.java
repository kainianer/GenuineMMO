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
import de.kainianer.genuine.entity.MMOPlayer;
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
import de.kainianer.genuine.events.onHealthRegen;
import de.kainianer.genuine.events.onPlayerInteract;
import de.kainianer.genuine.item.CustomItem;
import de.kainianer.genuine.util.RegionManager;
import de.kainianer.genuine.util.SpellManager;
import de.kainianer.genuine.util.TargetBarManager;
import de.kainianer.genuine.util.HungerManager;
import de.kainianer.genuine.util.ItemLoader;
import de.slikey.effectlib.EffectLib;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MainMMO extends JavaPlugin {

    private final TargetBarManager targetBarManger;
    private final SpellManager spellManager;
    private final Map<Item, CustomEffect> legendaryOnGroundList = new HashMap<>();
    private final Map<InventoryHolder, Inventory> quiverInventories = new HashMap<>();
    private final Map<String, CustomItem> itemList = new HashMap<>();
    private final Map<UUID, MMOPlayer> playerList = new HashMap<>();
    private final HungerManager hungerManager;
    private final RegionManager regionManager;

    public MainMMO() {
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
        pm.registerEvents(new onPlayerInteract(), this);
        pm.registerEvents(new onHealthRegen(), this);
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

    public static EffectLib getEffectLib() {
        return (EffectLib) Bukkit.getPluginManager().getPlugin("EffectLib");
    }

    public static MainMMO getInstance() {
        return (MainMMO) Bukkit.getPluginManager().getPlugin("GenuineMMO");
    }

    public Map<String, CustomItem> getItemList() {
        return this.itemList;
    }

    public SpellManager getSpellManager() {
        return this.spellManager;
    }
    
    public Map<UUID, MMOPlayer> getPlayers() {
        return this.playerList;
    }

}
