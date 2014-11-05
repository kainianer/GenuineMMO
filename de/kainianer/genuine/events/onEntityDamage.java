/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kainianer.genuine.events;

import de.kainianer.ui.Hologram;
import de.kainianer.genuine.item.BonusSpell;
import de.kainianer.genuine.item.Weapon;
import de.kainianer.ui.TargetBars;
import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 *
 * @author kainianer
 */
public class onEntityDamage implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof LivingEntity) {
            TargetBars.updateForEntity((LivingEntity) event.getEntity(), event.getFinalDamage());
           
            if (!(event.getEntity() instanceof Player)) {
                Hologram holo = new Hologram(1.8f, ChatColor.RED + "- " + (int) event.getFinalDamage());
                holo.show(event.getEntity().getLocation(), 10);
            }
        }
    }
}
