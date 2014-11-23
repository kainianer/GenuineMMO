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

import de.kainianer.mmo.effects.WandEffect;
import de.kainianer.mmo.events.WeaponUseEvent;
import de.kainianer.mmo.item.BonusSpell;
import de.kainianer.mmo.item.WeaponType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 *
 * @author kainianer
 */
public class onWeaponUse implements Listener {

    @EventHandler
    public void onWeaponUse(WeaponUseEvent event) {

        if (event.getWeapon().getWeaponType().equals(WeaponType.WAND) && event.getEntity() instanceof LivingEntity) {
            LivingEntity ent = (LivingEntity) event.getEntity();
            ent.damage(event.getWeapon().getDamage() * event.getMplayer().getBonusDamage(), event.getPlayer());
            if (event.getWeapon().getBonusSpells().isEmpty()) {
                WandEffect effect = new WandEffect(event.getPlayer());
                effect.startEffect();
            }
        }

        for (BonusSpell spell : event.getWeapon().getBonusSpells()) {
            spell.perform(event.getPlayer(), event.getEntity(), event.getWeapon());
        }
    }
}
