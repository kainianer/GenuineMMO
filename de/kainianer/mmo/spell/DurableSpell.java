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

import de.kainianer.mmo.item.Weapon;
import java.util.List;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 *
 * @author kainianer
 */
public abstract class DurableSpell {

    private int lived;
    private final int length;
    private final Player player;
    private final LivingEntity target;

    public DurableSpell(int length, Player player, LivingEntity entity) {
        this.length = length;
        this.player = player;
        this.target = entity;
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    public boolean isExpired() {
        if (this.length < 0) {
            return false;
        } else {
            return this.getLived() >= this.getLength();
        }
    }

    public void increaseLived() {
        this.lived++;
        this.tick();
    }

    public abstract void tick();

    public abstract void start();

    public abstract void cancel();

    /**
     * @return the lived
     */
    public int getLived() {
        return lived;
    }

    public LivingEntity getTarget() {
        return this.target;
    }

    public Player getPlayer() {
        return this.player;
    }
}
