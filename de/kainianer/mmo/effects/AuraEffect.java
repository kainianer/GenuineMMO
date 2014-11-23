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
package de.kainianer.mmo.effects;

import de.kainianer.mmo.main.Main;
import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.WarpEffect;
import de.slikey.effectlib.util.ParticleEffect;
import org.bukkit.Location;

/**
 *
 * @author kainianer
 */
public class AuraEffect extends WarpEffect {

    private double yOff;

    public AuraEffect() {
        super(new EffectManager(Main.getEffectLib()));
    }

    public void setYOff(double yOff) {
        this.yOff = yOff;
    }

    public void onRun() {
        Location location = getLocation();
        if (this.step > this.rings) {
            this.step = 0;
        }
        location.add(0.0D, this.yOff, 0.0D);
        for (int i = 0; i < this.particles; i++) {
            double angle = 6.283185307179586D * i / this.particles;
            double x = Math.cos(angle) * this.radius;
            double z = Math.sin(angle) * this.radius;
            location.add(x, 0.0D, z);
            this.particle.display(location, this.visibleRange);
            location.subtract(x, 0.0D, z);
        }
        location.subtract(0.0D, this.yOff, 0.0D);
        this.step += 1;
    }

    public void setParticles(ParticleEffect particleType) {
        this.particle = particleType;
    }
}
