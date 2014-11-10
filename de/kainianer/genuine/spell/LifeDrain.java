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

import de.kainianer.genuine.effects.CustomLineEffect;
import de.kainianer.genuine.Main;
import de.kainianer.genuine.item.BonusSpell;
import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.util.ParticleEffect;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 *
 * @author kainianer
 */
public class LifeDrain extends DurableSpell {

    private CustomLineEffect effect;
    private LivingEntity target;
    private Player player;

    public LifeDrain(int length, Player player, LivingEntity target) {
        super(4);
        this.target = target;
        this.player = player;
        effect = new CustomLineEffect(new EffectManager(Main.getEffectLib()));
        effect.particle = ParticleEffect.RED_DUST;
        effect.locationUpdateInterval = 1;
        effect.particles = 16;
        effect.setYOff(-0.5f);
        effect.infinite();
        effect.setEntity(player);
        effect.setTargetEntity(target);
    }

    @Override
    public void start() {
        this.effect.start();
        SpellManager.addSpell(this);
    }

    @Override
    public void cancel() {
        this.effect.cancel();
    }

    @Override
    public void tick() {
        if (!this.target.isDead() && this.player.getLocation().distance(this.target.getLocation()) < 8) {
            if (player.getHealth() <= player.getMaxHealth() - 3) {
                player.setHealth(player.getHealth() + 3d);
            } else {
                player.setHealth(20d);
            }
            this.target.damage(3);
        } else {
            this.effect.cancel();
        }
    }

    public static void perform(Player player, LivingEntity ent) {
        if (Spell.canPerform(player, BonusSpell.AUSSAUGEN)) {
            LifeDrain drain = new LifeDrain(4, player, ent);
            drain.start();
            Spell.removeHungerFromPlayer(player, BonusSpell.AUSSAUGEN);
        }
    }

}
