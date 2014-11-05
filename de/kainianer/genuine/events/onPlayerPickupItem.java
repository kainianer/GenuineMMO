/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kainianer.genuine.events;

import de.kainianer.genuine.Main;
import de.slikey.effectlib.EffectManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 *
 * @author kainianer
 */
public class onPlayerPickupItem implements Listener {

    @EventHandler
    public void onItemPickUp(PlayerPickupItemEvent event) {
        if (Main.getInstance().getLegendaryOnGroundList().containsKey(event.getItem())) {
            EffectManager em = Main.getInstance().getLegendaryOnGroundList().get(event.getItem());
            em.dispose();
            Main.getEffectLib().getEffectManagers().remove(em);
        }
    }
}
