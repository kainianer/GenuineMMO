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
package de.kainianer.mmo.item;

import java.util.Arrays;
import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author kainianer
 */
public class EnchantingBook {

    private final ItemStack stack = new ItemStack(Material.ENCHANTED_BOOK);
    private final String name = ChatColor.GREEN + "Fragment der alten Lehren";
    private final BonusSpell spell;

    public EnchantingBook() {
        this.spell = this.setRandomEnchant();
    }

    public ItemStack getBook() {
        ItemMeta meta = this.stack.getItemMeta();
        meta.setDisplayName(this.name);
        meta.setLore(Arrays.asList(ChatColor.GRAY + spell.getName() + ChatColor.ITALIC + " (" + spell.getHungerCost() + " Hunger)"));
        this.stack.setItemMeta(meta);
        return this.stack;
    }

    private BonusSpell setRandomEnchant() {
        Random ran = new Random();
        BonusSpell[] spells = BonusSpell.values();
        return spells[ran.nextInt(spells.length)];
    }

}
