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

import org.bukkit.entity.Player;

/**
 *
 * @author kainianer
 */
public abstract class DurableSpell extends Spell {

    private int lived;
    private final int length;
    private final double multi;
    private final Player source;

    public DurableSpell(int length, double multi, Player player) {
        super(player);
        this.length = length;
        this.multi = multi;
        this.source = player;
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    public boolean isExpired() {
        return this.getLived() >= this.getLength();
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

    /**
     * @return the multi
     */
    public double getMulti() {
        return multi;
    }

    /**
     * @return the source
     */
    public Player getSource() {
        return source;
    }

}
