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

import de.kainianer.genuine.MainMMO;
import de.kainianer.genuine.item.BonusSpell;
import de.kainianer.genuine.util.SpellUtil;
import de.kainianer.genuine.util.TargetBarManager;
import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.LineEffect;
import de.slikey.effectlib.util.ParticleEffect;
import java.util.Random;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 *
 * @author kainianer
 */
public class FireBall extends Spell {

    private FireBall(Player player) {
        super(player);
    }

    public static void perform(Player player, double damage) {
        if (Spell.canPerform(player, BonusSpell.FEUERBALL)) {
            LineEffect effect = new LineEffect(new EffectManager(MainMMO.getEffectLib()));
            effect.particle = ParticleEffect.EXPLODE;
            Random ran = new Random();
            effect.particles = ran.nextInt((7 - 3) + 1) + 3;
            effect.setTarget(player.getEyeLocation().add(player.getEyeLocation().getDirection().multiply(2f)));
            effect.setLocation(player.getEyeLocation().add(player.getEyeLocation().getDirection().multiply(0.5f)));
            effect.particles = 4;
            effect.start();
            Entity ent = SpellUtil.getTargetEntity(player);
            double distance = -1;
            if (ent != null) {
                distance = ent.getLocation().distance(player.getLocation());
            }
            if (ent instanceof LivingEntity && distance <= 16 && distance >= 0) {
                LivingEntity le = (LivingEntity) ent;
                le.damage(damage);
                TargetBarManager.updateForEntity(le, 0);
            }
            Spell.removeHungerFromPlayer(player, BonusSpell.FEUERBALL);
        }
    }

}
