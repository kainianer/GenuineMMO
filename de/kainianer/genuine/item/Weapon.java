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
