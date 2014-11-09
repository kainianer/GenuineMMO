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
import de.kainianer.genuine.item.BonusSpell;
import de.kainianer.genuine.item.Weapon;
import de.kainianer.genuine.spell.FireBall;
import de.kainianer.genuine.spell.IceLance;
import de.kainianer.genuine.spell.LifeDrain;
import de.kainianer.genuine.spell.Lift;
import de.kainianer.genuine.util.SpellUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
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
        if (!event.getPlayer().getItemInHand().getType().equals(Material.AIR)) {
            ItemStack stack = event.getPlayer().getItemInHand();
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

                if (stack.hasItemMeta()) {
                    if (stack.getItemMeta().hasDisplayName()) {
                        if (stack.getItemMeta().getDisplayName().contains("Köcher")) {
                            if (!Main.getInstance().getQuiverInventories().containsKey(event.getPlayer())) {
                                Player player = event.getPlayer();
                                Inventory inv = Bukkit.getServer().createInventory(player, 9, ChatColor.BLACK + "Köcher");
                                player.openInventory(inv);
                                event.setCancelled(true);
                            } else {
                                event.getPlayer().openInventory(Main.getInstance().getQuiverInventories().get(event.getPlayer()));
                            }
                        }
                    }
                }
            } else if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                if (stack.getItemMeta().hasDisplayName() && Main.getInstance().getItemList().containsKey(stack.getItemMeta().getDisplayName()) && stack.getType().equals(Material.BLAZE_ROD)) {
                    Weapon w = (Weapon) Main.getInstance().getItemList().get(stack.getItemMeta().getDisplayName());
                    Player player = event.getPlayer();
                    if (w.getBonusSpells().contains(BonusSpell.EISLANZE)) {
                        IceLance.perform(player);
                    }
                    if (w.getBonusSpells().contains(BonusSpell.FEUERBALL)) {
                        FireBall.perform(player);
                    }
                    if (w.getBonusSpells().contains(BonusSpell.SCHWUNG)) {
                        Entity ent = SpellUtil.getTargetEntity(event.getPlayer());
                        if (ent != null && ent instanceof LivingEntity) {
                            Lift.perform(player, (LivingEntity) ent);
                        }
                    }
                    if (w.getBonusSpells().contains(BonusSpell.AUSSAUGEN)) {
                        Entity ent = SpellUtil.getTargetEntity(player);
                        if (ent != null && ent instanceof LivingEntity) {
                            LifeDrain.perform(player, (LivingEntity) ent);
                        }
                    }
                }
            }
        }
    }
}
