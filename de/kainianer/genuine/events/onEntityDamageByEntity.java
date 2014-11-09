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
import de.kainianer.ui.TargetBarManager;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 *
 * @author kainianer
 */
public class onEntityDamageByEntity implements Listener {

    @EventHandler
    public void onEntityDamageLivingEntity(EntityDamageByEntityEvent event) {
        LivingEntity target = (LivingEntity) event.getEntity();

        if (event.getDamager() instanceof Projectile) {
            Projectile proj = (Projectile) event.getDamager();
            if (proj.getShooter() instanceof Player) {
                Player player = (Player) proj.getShooter();
                TargetBarManager.getInstance().setTargetOfPlayer(player, (LivingEntity) event.getEntity());
                if (player.getItemInHand().getType() == Material.BOW && proj instanceof Arrow) {
                    if (player.getItemInHand().getItemMeta().hasDisplayName()) {
                        if (Main.getInstance().getItemList().containsKey(player.getItemInHand().getItemMeta().getDisplayName())) {
                            Weapon w = (Weapon) Main.getInstance().getItemList().get(player.getItemInHand().getItemMeta().getDisplayName());
                            if (w.getBonusSpells().contains(BonusSpell.GIFTPFEIL)) {
                                if (w.getBonusSpells().contains(BonusSpell.EXPLOSIVSCHUSS)) {
                                    for (Entity ent : target.getNearbyEntities(2, 2, 2)) {
                                        if (ent instanceof LivingEntity) {
                                            LivingEntity le = (LivingEntity) ent;
                                            le.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 1));
                                        }
                                    }
                                    target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1));
                                } else {
                                    target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1));
                                }
                            }

                        }
                    }
                }
            }
        } else if (event.getDamager() instanceof Player) {
            TargetBarManager.getInstance().setTargetOfPlayer((Player) event.getDamager(), (LivingEntity) event.getEntity());
        }
    }
}
