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
package de.kainianer.genuine.spell;

import de.kainianer.genuine.item.BonusSpell;
import de.kainianer.genuine.util.TargetBarManager;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 *
 * @author kainianer
 */
public class ExplosiveArrow extends Spell {

    private ExplosiveArrow(Player player) {
        super(player);
    }

    public static void perform(Player p, double damage, LivingEntity entity) {
        if (Spell.canPerform(p, BonusSpell.EXPLOSIVSCHUSS)) {
            Location loc = entity.getLocation();
            loc.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), 2f, false, false);
            for (Entity e : entity.getNearbyEntities(2f, 2f, 2f)) {
                if (!(e instanceof LivingEntity) || e instanceof Player) {
                    continue;
                }
                LivingEntity ent = (LivingEntity) e;
                ent.damage(damage);
                TargetBarManager.updateForEntity(ent, damage);
            }
            entity.damage(damage);
            TargetBarManager.updateForEntity(entity, damage);
            Spell.removeHungerFromPlayer(p, BonusSpell.EXPLOSIVSCHUSS);
        }
    }

}
