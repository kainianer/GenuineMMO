/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
