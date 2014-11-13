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

import de.kainianer.genuine.MainMMO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author kainianer
 */
public class CustomItem extends ItemStack implements ConfigurationSerializable {
    
    private final List<Stat> stats;
    private final String name;
    private final Rarity rarity;
    private String lore;
    
    CustomItem(Material mat, String name, Rarity rarity, List<Stat> stats, String lore) {
        super(mat);
        this.stats = stats;
        this.lore = lore;
        if (lore != null && !"".equals(lore)) {
            this.lore = "\"" + this.lore + "\"";
        }
        this.rarity = rarity;
        this.name = name;
    }

    /**
     * @return the stats
     */
    public List<Stat> getStats() {
        return stats;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the lore
     */
    public String getLore() {
        return this.lore;
    }

    /**
     * @return the rarity
     */
    public Rarity getRarity() {
        return this.rarity;
    }
    
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
        if (!"".equals(this.getLore())) {
            itemLore.add("");
            for (String str : this.getLoreAsList()) {
                itemLore.add(ChatColor.GRAY + str);
            }
        }
        data.setLore(itemLore);
        this.setItemMeta(data);
    }

    /**
     * @return returns the lore split with \n
     */
    public List<String> getLoreAsList() {
        return Arrays.asList(this.lore.split("\n#"));
    }
}
