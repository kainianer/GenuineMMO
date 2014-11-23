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
package de.kainianer.mmo.spells;

import de.kainianer.mmo.main.Main;
import de.kainianer.mmo.effects.CustomLineEffect;
import de.kainianer.mmo.item.Weapon;
import de.kainianer.mmo.spell.DamageOnTimeSpell;
import de.kainianer.mmo.spell.Spell;
import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.util.ParticleEffect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 *
 * @author kainianer
 */
public class Aussaugen extends Spell {

    @Override
    public void perform(Player player, Entity entity, Weapon w) {
        if (entity instanceof LivingEntity) {
            DamageOnTimeSpell dot = new DamageOnTimeSpell(4, player, (LivingEntity) entity, w) {
                private CustomLineEffect effect;

                @Override
                public void start() {
                    this.effect = new CustomLineEffect(new EffectManager(Main.getEffectLib()));
                    effect.particle = ParticleEffect.RED_DUST;
                    effect.particles = 8;
                    effect.infinite();
                    effect.setEntity(this.getPlayer());
                    effect.setTargetEntity(this.getTarget());
                    effect.setYOff(-0.25f);
                    effect.start();
                    super.start();
                }

                @Override
                public void tick() {
                    super.tick();
                    if (this.getTarget().isDead()) {
                        this.effect.cancel();
                    } else if (this.getPlayer().getHealth() + 3 <= this.getPlayer().getMaxHealth()) {
                        this.getPlayer().setHealth(this.getPlayer().getHealth() + 3);
                    }
                }

                @Override
                public void cancel() {
                    this.effect.cancel();
                    super.cancel();
                }
            };
            dot.start();
        }
    }

}
