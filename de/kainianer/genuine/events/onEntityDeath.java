/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kainianer.genuine.events;

import de.kainianer.ui.Hologram;
import de.kainianer.genuine.item.BonusSpell;
import de.kainianer.genuine.item.Rarity;
import de.kainianer.genuine.item.Stat;
import de.kainianer.genuine.item.Weapon;
import de.kainianer.genuine.item.WeaponType;
import de.kainianer.ui.TargetBars;
import java.util.Arrays;
import org.bukkit.ChatColor;
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
        TargetBars.updateForEntity(event.getEntity(), 0);

        Weapon item = new Weapon(WeaponType.BOW, "Etrayu", Rarity.LEGENDÄR, Arrays.asList(new Stat(Stat.StatType.SCHADEN, 89, false), new Stat(Stat.StatType.ERFAHRUNG, 15, true), new Stat(Stat.StatType.LEBENSRAU, 2, false), new Stat(Stat.StatType.HUNGERREG, 2, false), new Stat(Stat.StatType.LEVEL, 65, false)), Arrays.asList(BonusSpell.FEUERPFEILE, BonusSpell.UNENDLICHKEIT), "Man sagt es gibt nur eine einzig wahre Waffe für jeden Helden.");
        event.getDrops().clear();
        event.getDrops().add(item);

        Hologram holo = new Hologram(2, ChatColor.GREEN + "+ " + event.getDroppedExp() + " EXP");
        holo.show(event.getEntity().getLocation(), 30);

    }
}
