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

import de.kainianer.mmo.effects.SmokeEffect;
import de.kainianer.mmo.effects.WandEffect;
import de.kainianer.mmo.events.WeaponUseEvent;
import de.kainianer.mmo.item.Weapon;
import de.kainianer.mmo.item.WeaponType;
import de.kainianer.mmo.util.ItemUtil;
import de.kainianer.mmo.util.SpellUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 *
 * @author kainianer
 */
public class onItemUse implements Listener {

    @EventHandler
    public void onItemUse(PlayerInteractEvent event) {
        if (ItemUtil.verifyCustomItem(event.getItem())) {
            Weapon w = ItemUtil.getCustomWeaponItem(event.getItem());
            if (w != null && w.getWeaponType().equals(WeaponType.SPEAR)) {
                Bukkit.getServer().getPluginManager().callEvent(new WeaponUseEvent(event.getPlayer(), event.getItem(), null));
            } else if (w != null && w.getWeaponType().equals(WeaponType.WAND)) {
                LivingEntity target = SpellUtil.getTargetLivingEntity(event.getPlayer());
                if (target != null && target.getLocation().distance(event.getPlayer().getLocation()) < 16) {
                    WandEffect effect = new WandEffect(event.getPlayer());
                    effect.startEffect();
                    Bukkit.getServer().getPluginManager().callEvent(new WeaponUseEvent(event.getPlayer(), event.getItem(), SpellUtil.getTargetLivingEntity(event.getPlayer())));
                }
            }
        }
        if (event.getItem() != null && !event.getItem().getType().equals(Material.BOW)) {
            event.getItem().setDurability((short) 0);
        }

        if (event.getItem() != null && event.getItem().getType().equals(Material.TORCH) && event.getItem().hasItemMeta() && event.getItem().getItemMeta().hasDisplayName()) {
            if (event.getItem().getItemMeta().getDisplayName().contains("Sumpfkraut")) {
                event.getPlayer().getInventory().remove(event.getItem());
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 1000, 2));
                SmokeEffect effect = new SmokeEffect(event.getPlayer());
                effect.start();
            }
        }
    }
}
