/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kainianer.genuine.item;

import org.bukkit.Material;

/**
 *
 * @author kainianer
 */
public enum WeaponType {
    
    BOW(Material.BOW),
    SPEAR(Material.IRON_SPADE),
    WAND(Material.BLAZE_ROD);
    
    private final Material mat;
    
    private WeaponType(Material mat) {
        this.mat = mat;
    }
    
    public Material getMaterial() {
        return this.mat;
    }
    
}
