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
package de.kainianer.mmo.spell;

import de.kainianer.mmo.effects.AuraEffect;
import de.kainianer.mmo.entity.MMOPlayer;
import de.kainianer.mmo.item.Weapon;
import de.kainianer.mmo.managers.SpellManager;
import de.slikey.effectlib.util.ParticleEffect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 *
 * @author kainianer
 */
public class Aura extends DurableSpell {
    
    private AuraEffect effect;
    private Weapon w;
    private MMOPlayer mplayer;
    private AuraType type;
    
    public enum AuraType {
        
        GIFTAURA;
        
    }
    
    public Aura(Player player, Weapon w, AuraType type) {
        super(-1, player, null);
        this.w = w;
        this.mplayer = MMOPlayer.wrapPlayer(player);
        this.type = type;
        this.effect = new AuraEffect();
        
        this.effect.setEntity(player);
        this.effect.setYOff(-1.62f);
        this.effect.setEntity(player);
        this.effect.particles = 8;
        this.effect.particle = ParticleEffect.WITCH_MAGIC;
        this.effect.radius = 2;
        this.effect.infinite();
    }
    
    @Override
    public void tick() {
        for (Entity entity : this.getPlayer().getNearbyEntities(2, 2, 2)) {
            if (entity instanceof LivingEntity && !entity.getUniqueId().equals(this.getPlayer().getUniqueId())) {
                ((LivingEntity) entity).damage(this.w.getMagicDamage() * this.mplayer.getBonusDamage(), this.getPlayer());
            }
        }
    }
    
    @Override
    public void start() {
        SpellManager.addSpell(this);
        this.effect.start();
    }
    
    @Override
    public void cancel() {
        this.effect.cancel();
    }
    
    public AuraType getType() {
        return this.type;
    }
    
}
