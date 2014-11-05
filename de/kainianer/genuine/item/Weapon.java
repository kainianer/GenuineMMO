/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kainianer.genuine.item;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author kainianer
 */
public final class Weapon extends CustomItem {

    private final List<BonusSpell> bonusSpells;

    public Weapon(WeaponType type, String name, Rarity rarity, List<Stat> stats, List<BonusSpell> bonusSpells, String lore) {
        super(type.getMaterial(), name, rarity, stats, lore);
        this.bonusSpells = bonusSpells;
        this.createItemMeta();
    }

    public List<BonusSpell> getBonusSpells() {
        return this.bonusSpells;
    }

    @Override
    public void createItemMeta() {
        ItemMeta data = this.getItemMeta();
        List<String> itemLore = new ArrayList<>();
        data.setDisplayName(this.getRarity().getChatColor() + "" + ChatColor.BOLD + this.getName());
        itemLore.add(ChatColor.GRAY + this.getRarity().getName());
        if (this.getStats().size() > 0) {
            itemLore.add("");
            for (Stat stat : this.getStats()) {
                itemLore.add(ChatColor.WHITE + stat.getStatName() + ": " + ChatColor.GRAY + stat.getValueAsString());
            }
        }
        if (this.getBonusSpells().size() > 0) {
            itemLore.add("");
            for (BonusSpell spell : this.getBonusSpells()) {
                itemLore.add(ChatColor.RED + ">> " + spell.getName());
            }
        }
        if (!"".equals(this.getLore())) {
            itemLore.add("");
            for (String str : this.getLoreAsList()) {
                itemLore.add(ChatColor.GRAY + str);
            }
        }
        data.setLore(itemLore);
        this.setItemMeta(data);
    }
}
