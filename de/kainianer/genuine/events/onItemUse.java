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
