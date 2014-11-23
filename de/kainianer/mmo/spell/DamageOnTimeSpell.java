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

import de.kainianer.mmo.entity.MMOPlayer;
import de.kainianer.mmo.item.Weapon;
import de.kainianer.mmo.managers.SpellManager;
import de.kainianer.mmo.managers.TargetBarManager;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 *
 * @author kainianer
 */
public class DamageOnTimeSpell extends DurableSpell {

    private Weapon w;
    private MMOPlayer player;

    public DamageOnTimeSpell(int length, Player player, LivingEntity entity, Weapon w) {
        super(length, player, entity);
        this.w = w;
        this.player = MMOPlayer.wrapPlayer(player);
    }

    @Override
    public void tick() {
        TargetBarManager.updateForEntity(this.getTarget(), this.w.getMagicDamage() * this.player.getBonusDamage());
        this.getTarget().damage(this.w.getMagicDamage() * player.getBonusDamage());
    }

    @Override
    public void start() {
        SpellManager.addSpell(this);
    }

    @Override
    public void cancel() {
    }
}
