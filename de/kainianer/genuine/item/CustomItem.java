/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kainianer.genuine.item;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author kainianer
 */
public class CustomItem extends ItemStack {

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
        return rarity;
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
     * @return return the item lore capped to 4 word per row
     */
    public List<String> getLoreAsList() {
        List<String> list = new ArrayList<>();
        String[] string = this.getLore().split(" ");
        String str = "";
        for (String st : string) {
            str += st + " ";
            if (str.split(" ").length == 4) {
                list.add(str);
                str = "";
            }
        }
        return list;
    }
}
