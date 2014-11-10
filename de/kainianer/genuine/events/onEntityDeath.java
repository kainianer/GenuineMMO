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

import de.kainianer.genuine.util.TargetBarManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 *
 * @author kainianer
 */
public class onEntityDeath implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        TargetBarManager.updateForEntity(event.getEntity(), 0);

        /*
         Weapon item = new Weapon(WeaponType.BOW, "Etrayu", Rarity.LEGENDÄR, Arrays.asList(new Stat(Stat.StatType.SCHADEN, 89, false), new Stat(Stat.StatType.ERFAHRUNG, 15, true), new Stat(Stat.StatType.LEBENSRAU, 2, false), new Stat(Stat.StatType.HUNGERREG, 2, false), new Stat(Stat.StatType.LEVEL, 65, false)), Arrays.asList(BonusSpell.FEUERPFEILE, BonusSpell.UNENDLICHKEIT), "Man sagt es gibt nur eine einzig wahre Waffe für jeden Helden.");
         event.getDrops().clear();
         event.getDrops().add(item);
         
         Hologram holo = new Hologram(2, ChatColor.GREEN + "+ " + event.getDroppedExp() + " EXP");
         holo.show(event.getEntity().getLocation(), 30);
         */
    }
}
