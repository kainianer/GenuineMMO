/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kainianer.genuine.events;

import de.kainianer.genuine.Main;
import de.kainianer.ui.Hologram;
import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.LineEffect;
import de.slikey.effectlib.util.ParticleEffect;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;

/**
 *
 * @author kainianer
 */
public class onItemSpawn implements Listener {

    @EventHandler
    public void onItemSpawn(ItemSpawnEvent event) {
        if (event.getEntity().getItemStack().hasItemMeta()) {
            if (event.getEntity().getItemStack().getItemMeta().hasDisplayName()) {
                if (event.getEntity().getItemStack().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD)) {
                    EffectManager em = new EffectManager(Main.getEffectLib());
                    LineEffect effect = new LineEffect(em);
                    effect.particle = ParticleEffect.PORTAL;
                    effect.infinite();
                    effect.particles = 256;
                    effect.setEntity(event.getEntity());
                    effect.setTarget(event.getEntity().getLocation().add(0, event.getEntity().getWorld().getMaxHeight() - event.getEntity().getLocation().getY(), 0));
                    effect.start();
                    Main.getInstance().getLegendaryOnGroundList().put(event.getEntity(), em);
                }
            }
        }
    }

}
