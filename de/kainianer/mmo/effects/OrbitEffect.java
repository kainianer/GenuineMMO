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

import de.kainianer.mmo.effect.CustomEffect;
import de.kainianer.mmo.main.Main;
import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.CircleEffect;
import de.slikey.effectlib.util.ParticleEffect;

/**
 *
 * @author kainianer
 */
public class OrbitEffect extends CustomEffect {

    public OrbitEffect() {
        super(new CircleEffect(new EffectManager(Main.getEffectLib())));
        ((CircleEffect) this.getEffect()).radius = 1f;
        ((CircleEffect) this.getEffect()).angularVelocityX = 0;
        ((CircleEffect) this.getEffect()).angularVelocityZ = 0;
        ((CircleEffect) this.getEffect()).particles = 1;
        ((CircleEffect) this.getEffect()).angularVelocityY = 5;
    }

    @Override
    public void setParticle(ParticleEffect particle) {
        ((CircleEffect) this.getEffect()).particle = particle;
    }
}
