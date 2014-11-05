/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kainianer.genuine.events;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 *
 * @author kainianer
 */
public class onInventoryClick implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        List<InventoryAction> events = new ArrayList<>();
        events.add(InventoryAction.PLACE_ALL);
        events.add(InventoryAction.PLACE_ONE);
        events.add(InventoryAction.PLACE_SOME);
        if (inv.getName().contains("Quiver")) {
            if (event.getRawSlot() < 9) {
                if (event.getCursor().getType() != Material.ARROW) {
                    if (event.getCursor().getType() != Material.AIR) {
                        event.setCancelled(true);
                    }
                }
            } else {
                if (event.isShiftClick() && event.getCurrentItem().getType() != Material.ARROW) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
