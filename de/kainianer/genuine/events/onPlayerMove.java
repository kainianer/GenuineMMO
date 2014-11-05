/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kainianer.genuine.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 *
 * @author kainianer
 */
public class onPlayerMove implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        /*Player player = event.getPlayer();
        if(Main.getInstance().getEffectPassengers().containsKey(player.getUniqueId())) {
            WitherSkull skull = Main.getInstance().getEffectPassengers().get(player.getUniqueId());
            ((Entity)skull).teleport(player);
        }*/
    }

}
