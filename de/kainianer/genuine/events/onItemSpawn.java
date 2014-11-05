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
package de.kainianer.genuine.events;

import de.kainianer.genuine.Main;
import de.kainianer.ui.Hologram;
import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.LineEffect;
import de.slikey.effectlib.util.ParticleEffect;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;

/**
 *
 * @author kainianer
 */
public class onItemSpawn implements Listener {

    @EventHandler
    public void onItemSpawn(ItemSpawnEvent event) {
        if (event.getEntity().getItemStack().hasItemMeta()) {
            if (event.getEntity().getItemStack().getItemMeta().hasDisplayName()) {
                if (event.getEntity().getItemStack().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "" + ChatColor.BOLD)) {
                    EffectManager em = new EffectManager(Main.getEffectLib());
                    LineEffect effect = new LineEffect(em);
                    effect.particle = ParticleEffect.PORTAL;
                    effect.infinite();
                    effect.particles = 256;
                    effect.setEntity(event.getEntity());
                    effect.setTarget(event.getEntity().getLocation().add(0, event.getEntity().getWorld().getMaxHeight() - event.getEntity().getLocation().getY(), 0));
                    effect.start();
                    Main.getInstance().getLegendaryOnGroundList().put(event.getEntity(), em);
                }
            }
        }
    }

}
