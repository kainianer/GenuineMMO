/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kainianer.genuine.item;

import java.util.List;
import org.bukkit.Material;

/**
 *
 * @author kainianer
 */
public class Armor extends CustomItem {

    public Armor(Material mat, String name, Rarity rarity, List<Stat> stats, String lore) {
        super(mat, name, rarity, stats, lore);
    }
    
}
