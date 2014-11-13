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

import de.kainianer.genuine.item.Gem;
import de.kainianer.genuine.item.Weapon;
import de.kainianer.genuine.util.ItemUtil;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author kainianer
 */
public class onInventoryClick implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        /* if (event.getInventory().getType().equals(InventoryType.BREWING)) {
            if (event.getRawSlot() < 4) {
                if (!ItemUtil.verifyCustomItem(event.getCursor())) {
                    if (event.getCursor().getType() != Material.AIR && !event.getCursor().getType().equals(Material.EMERALD)) {
                        event.setCancelled(true);

                    } else {
                        event.setCursor(event.getInventory().getItem(event.getRawSlot()));
                        event.getInventory().setItem(event.getRawSlot(), new ItemStack(Material.AIR));
                        event.setCancelled(true);
                    }
                } else if (event.getRawSlot() == 3) {
                    if (ItemUtil.verifyCustomItem(event.getInventory().getItem(event.getRawSlot()))) {
                        Weapon w = ItemUtil.getCustomWeaponItem(event.getInventory().getItem(3));
                        for (int i = 0; i < 3; i++) {
                            if (event.getInventory().getItem(i).getType() == Material.INK_SACK) {
                                Gem gem = ItemUtil.getCustomGemItem(event.getInventory().getItem(i));
                                if (!w.hasMaxEnchantments()) {
                                    w.addBonusSpell(gem.getBonusSpell());
                                    event.getInventory().setItem(i, new ItemStack(Material.AIR));
                                }
                            }
                        }
                    }
                    event.getInventory().setItem(3, event.getCursor());
                    event.setCursor(new ItemStack(Material.AIR));
                    event.setCancelled(true);
                } else if (event.getCursor().getType().equals(Material.EMERALD)) {
                    if (event.getRawSlot() < 3) {
                        event.getInventory().setItem(event.getRawSlot(), event.getCursor());
                        event.setCursor(new ItemStack(Material.AIR));
                        event.setCancelled(true);
                    } else {
                        event.setCancelled(true);
                    }
                } else {
                    event.setCancelled(true);
                }
            } else {
                if (event.isShiftClick() && !ItemUtil.verifyCustomItem(event.getCursor())) {
                    event.setCancelled(true);
                }
            }
        } */

        //Quiverhandling
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
