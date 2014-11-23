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
package de.kainianer.mmo.main;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import de.kainianer.mmo.commands.GroupCommand;
import de.kainianer.mmo.commands.TestCommand;
import de.kainianer.mmo.eventListener.onBowShoot;
import de.kainianer.mmo.eventListener.onEntityDamage;
import de.kainianer.mmo.eventListener.onEntityDamageByEntity;
import de.kainianer.mmo.eventListener.onEntityDeath;
import de.kainianer.mmo.eventListener.onItemUse;
import de.kainianer.mmo.eventListener.onEntityDamageByPlayer;
import de.kainianer.mmo.eventListener.onEntityTarget;
import de.kainianer.mmo.eventListener.onEntityTargetLivingEntity;
import de.kainianer.mmo.eventListener.onEntityTargetPlayer;
import de.kainianer.mmo.eventListener.onExperienceChange;
import de.kainianer.mmo.eventListener.onInventoryInteract;
import de.kainianer.mmo.eventListener.onPlayerChat;
import de.kainianer.mmo.eventListener.onPlayerDeath;
import de.kainianer.mmo.eventListener.onPlayerJoin;
import de.kainianer.mmo.eventListener.onPlayerQuit;
import de.kainianer.mmo.eventListener.onPlayerRespawn;
import de.kainianer.mmo.eventListener.onWeaponUse;
import de.kainianer.mmo.managers.RegionManager;
import de.kainianer.mmo.managers.SpellManager;
import de.kainianer.mmo.managers.HungerManager;
import de.kainianer.mmo.managers.TargetBarManager;
import de.slikey.effectlib.EffectLib;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        //loading events
        System.out.println("[GenuineMMO] Loading events ...");
        PluginManager pm = this.getServer().getPluginManager();

        pm.registerEvents(new onItemUse(), this);
        pm.registerEvents(new onBowShoot(), this);
        pm.registerEvents(new onEntityDamage(), this);
        pm.registerEvents(new onEntityDamageByEntity(), this);

        pm.registerEvents(new onEntityDeath(), this);
        pm.registerEvents(new onPlayerDeath(), this);
        pm.registerEvents(new onPlayerRespawn(), this);

        pm.registerEvents(new onPlayerChat(), this);
        pm.registerEvents(new onPlayerJoin(), this);
        pm.registerEvents(new onPlayerQuit(), this);
        pm.registerEvents(new onInventoryInteract(), this);
        pm.registerEvents(new onExperienceChange(), this);

        pm.registerEvents(new onEntityTarget(), this);
        pm.registerEvents(new onEntityTargetLivingEntity(), this);

        pm.registerEvents(new onEntityTargetPlayer(), this);
        pm.registerEvents(new onEntityDamageByPlayer(), this);
        pm.registerEvents(new onWeaponUse(), this);
        System.out.println("[GenuineMMO] Events loaded");

        //adding commmands
        this.getCommand("test").setExecutor(new TestCommand());
        this.getCommand("group").setExecutor(new GroupCommand());

        System.out.println("[GenuineMMO] Loading managers ...");
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, SpellManager.getInstance(), 0, 20);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, TargetBarManager.getInstance(), 0, 20);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, HungerManager.getInstance(), 0, 40);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, RegionManager.getInstance(), 0, 200);
        System.out.println("[GenuineMMO] Managers loaded");

        System.out.println("[GenuineMMO] Successfully enabled");

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                System.out.println("Saving players ...");
                Bukkit.getServer().savePlayers();
                System.out.println("Saving worlds ...");
                for (World world : Bukkit.getWorlds()) {
                    System.out.println("World " + world.getName() + " saved!");
                    world.save();
                }
            }
        }, 60, 3600);
    }

    @Override
    public void onDisable() {
        System.out.println("[GenuineMMO] Successfully disabled!");
    }

    public static EffectLib getEffectLib() {
        return (EffectLib) Bukkit.getPluginManager().getPlugin("EffectLib");
    }

    public static Main getInstance() {
        return (Main) Bukkit.getPluginManager().getPlugin("GenuineMMO");
    }

    public WorldGuardPlugin getWorldGuard() {
        return (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
    }

}
