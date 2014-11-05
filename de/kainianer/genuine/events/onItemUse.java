/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kainianer.genuine.events;

import de.kainianer.genuine.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author kainianer
 */
public class onItemUse implements Listener {

    @EventHandler
    public void onItemUse(PlayerInteractEvent event) {
        ItemStack stack = event.getPlayer().getItemInHand();
        if (stack.hasItemMeta()) {
            if (stack.getItemMeta().hasDisplayName()) {
                if (stack.getItemMeta().getDisplayName().contains("Quiver")) {
                    if (!Main.getInstance().getQuiverInventories().containsKey(event.getPlayer())) {
                        Player player = event.getPlayer();
                        Inventory inv = Bukkit.getServer().createInventory(player, 9, ChatColor.BLACK + "Quiver");
                        player.openInventory(inv);
                        event.setCancelled(true);
                    } else {
                        event.getPlayer().openInventory(Main.getInstance().getQuiverInventories().get(event.getPlayer()));
                    }
                }
            }
        }
    }
}
