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

import de.kainianer.genuine.item.BonusSpell;
import de.kainianer.genuine.item.Weapon;
import de.kainianer.genuine.item.WeaponType;
import de.kainianer.genuine.spell.ExplosiveArrow;
import de.kainianer.genuine.spell.PoisonArrow;
import de.kainianer.genuine.util.ItemUtil;
import de.kainianer.genuine.util.TargetBarManager;
import net.minecraft.server.v1_7_R4.Explosion;
import org.bukkit.Location;
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
        Player player = event.getDamager() instanceof Projectile ? (Player) ((Projectile) event.getDamager()).getShooter() : (Player) event.getDamager();
        if (player != null && ItemUtil.verifyCustomItem(player.getItemInHand())) {
            Weapon w = ItemUtil.getCustomWeaponItem(player.getItemInHand());
            if (w.getWeaponType() != WeaponType.WAND) {
                event.setDamage(w.getDamage());
                if (w.hasSpell(BonusSpell.EXPLOSIVSCHUSS)) {
                    ExplosiveArrow.perform(player, w.getDamage(), target);
                    if (w.hasSpell(BonusSpell.GIFTPFEIL)) {
                        for (Entity entity : event.getEntity().getNearbyEntities(1f, 1f, 1f)) {
                            if (entity instanceof LivingEntity && entity instanceof Player && !((Player) entity).getUniqueId().equals(player.getUniqueId())) {
                                PoisonArrow.perform(player, w.getDamage(), (LivingEntity) entity);
                            }
                        }
                    }
                }
                if (w.hasSpell(BonusSpell.GIFTPFEIL) && !w.hasSpell(BonusSpell.EXPLOSIVSCHUSS)) {
                    PoisonArrow.perform(player, w.getDamage(), target);
                }
            } else {
                event.setDamage(1);
            }
        }
        if (event.getEntity() != null) {
            TargetBarManager.getInstance().setTargetOfPlayer(player, target);
            TargetBarManager.updateForEntity(target, event.getFinalDamage());
        }
    }
}
