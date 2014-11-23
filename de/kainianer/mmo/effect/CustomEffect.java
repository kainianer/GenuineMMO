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
package de.kainianer.mmo.effect;

import de.slikey.effectlib.Effect;
import de.slikey.effectlib.util.ParticleEffect;
import org.bukkit.entity.Entity;

/**
 *
 * @author kainianer
 */
public abstract class CustomEffect {

    public Effect effect;

    public CustomEffect(Effect effect) {
        this.effect = effect;
    }

    public void setSource(Entity source) {
        this.effect.setEntity(source);
    }

    public void setDuration(double seconds) {
        if (seconds == -1) {
            this.effect.infinite();
        } else {
            this.effect.iterations = (int) (seconds * 20);
            this.effect.period = 1;
        }
    }

    public void cancel() {
        this.effect.cancel();
    }

    public void startEffect() {
        this.effect.start();
    }

    public Effect getEffect() {
        return this.effect;
    }

    public abstract void setParticle(ParticleEffect particle);

}
