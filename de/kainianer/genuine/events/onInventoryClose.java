/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kainianer.genuine.events;

import de.kainianer.genuine.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

/**
 *
 * @author kainianer
 */
public class onInventoryClose implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Inventory inv = event.getInventory();
        if (inv.getTitle().contains("Quiver")) {
            Main.getInstance().getQuiverInventories().put(event.getInventory().getHolder(), inv);
        }
    }

}
