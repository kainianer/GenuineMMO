/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kainianer.genuine.events;

import org.bukkit.Color;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

/**
 *
 * @author kainianer
 */
public class onEntityTarget implements Listener {

    @EventHandler
    public void onEntityTarget(EntityTargetEvent event) {

        //must be declared, because spiders do not fire entityTargetLivingEntityEvent
        if (event.getEntity() instanceof Spider) {
            Entity target = event.getTarget();
            if (target instanceof Player) {
                Player player = (Player) target;
                boolean valid = true;
                Color color = Color.fromRGB(114, 113, 57);
                for (ItemStack is : player.getInventory().getArmorContents()) {
                    if (is.getItemMeta() instanceof LeatherArmorMeta) {
                        LeatherArmorMeta meta = (LeatherArmorMeta) is.getItemMeta();
                        if (meta.getColor().asRGB() != color.asRGB()) {
                            valid = false;
                            break;
                        }
                    } else {
                        valid = false;
                        break;
                    }

                }
                event.setCancelled(valid);
            }
        }
    }
}
