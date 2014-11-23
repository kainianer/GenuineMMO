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
package de.kainianer.mmo.eventListener;

import de.kainianer.mmo.events.EntityDamageByPlayerEvent;
import de.kainianer.mmo.managers.TargetBarManager;
import de.kainianer.mmo.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author kainianer
 */
public class onEntityDamageByEntity implements Listener {

    @EventHandler
    public void onEntityDamageLivingEntity(EntityDamageByEntityEvent event) {
        LivingEntity target = (LivingEntity) event.getEntity();
        Player player = event.getDamager() instanceof Projectile ? (Player) ((Projectile) event.getDamager()).getShooter() : (event.getDamager() instanceof Player ? (Player) event.getDamager() : null);
        if (player != null) {
            TargetBarManager.getInstance().setTargetOfPlayer(player, target);
            for (ItemStack stack : player.getInventory().getArmorContents()) {
                if (stack != null) {
                    stack.setDurability((short) 0);
                }
            }
            if (!target.getUniqueId().equals(player.getUniqueId())) {
                if (ItemUtil.verifyCustomItem(player.getItemInHand()) && ItemUtil.getCustomWeaponItem(player.getItemInHand()) != null && !(!(event.getDamager() instanceof Projectile) && player.getItemInHand().getType().equals(Material.BOW))) {
                    Bukkit.getServer().getPluginManager().callEvent(new EntityDamageByPlayerEvent(player, target, player.getItemInHand()));
                    event.setDamage(ItemUtil.getCustomWeaponItem(player.getItemInHand()).getDamage());
                }
            } else {
                event.setDamage(0d);
            }
        }
        TargetBarManager.updateForEntity(target, event.getFinalDamage());
    }
}
