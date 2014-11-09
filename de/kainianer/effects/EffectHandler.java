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
package de.kainianer.effects;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author kainianer
 */
public final class EffectHandler {

    private final Map<Integer, CustomEffect> effects = new HashMap<>();
    private final JavaPlugin plugin;

    public EffectHandler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void addEffect(CustomEffect effect) {
        this.effects.put(this.effects.size() + 1, effect);
    }

    public Map<Integer, CustomEffect> getCustomEffects(CustomEffect effect) {
        return this.effects;
    }

    public void cancelEffect(CustomEffect effect) {
        CustomEffect ef = this.effects.remove(effect);
        ef.cancel();
    }
}
