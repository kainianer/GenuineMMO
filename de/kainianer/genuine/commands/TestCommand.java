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
package de.kainianer.genuine.commands;

import de.kainianer.genuine.item.BonusSpell;
import de.kainianer.genuine.item.Rarity;
import de.kainianer.genuine.item.Stat;
import de.kainianer.genuine.item.Stat.StatType;
import de.kainianer.genuine.item.Weapon;
import de.kainianer.genuine.item.WeaponType;
import java.util.Arrays;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author kainianer
 */
public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            /*WitherSkull skull = (WitherSkull) p.getWorld().spawn(p.getLocation().add(0, 2.5, 0), WitherSkull.class);
             skull.setDirection(new Vector(0, 0, 0));
             skull.setVelocity(new Vector(0, 0, 0));
             ItemStack mat = new ItemStack(Material.SKULL);
             Item drop = p.getWorld().dropItem(p.getLocation(), mat);
             drop.setPickupDelay(20 * 300);
             //Entity drop = p.getWorld().spawn(p.getLocation().add(0,3,0), Bat.class);
             skull.setPassenger(drop);
             Main.getInstance().getEffectPassengers().put(p.getUniqueId(), skull); */
            List<Stat> stat = Arrays.asList(new Stat(StatType.SCHADEN, 108, false), new Stat(StatType.LEBENSRAU, 4, false), new Stat(StatType.ERFAHRUNG, 12, true), new Stat(StatType.LEVEL, 75, false));
            List<BonusSpell> bsl = Arrays.asList(BonusSpell.SPLITTERPFEIL, BonusSpell.GIFTPFEIL, BonusSpell.LEBENSRAUB,BonusSpell.FESSELN);
            Weapon w = new Weapon(WeaponType.BOW, "Jochens überspannter Bogen", Rarity.LEGENDÄR, stat, bsl, "Häufige Nebeneffekte: Vergesslichkeit und Orientierungslosigkeit");
            p.getInventory().addItem(w);
        }
        return false;
    }

}
