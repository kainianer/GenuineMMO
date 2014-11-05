/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kainianer.genuine.item;

import org.bukkit.ChatColor;

/**
 *
 * @author kainianer
 */
public enum Rarity {

    HÄUFIG(ChatColor.GRAY),
    NORMAL(ChatColor.WHITE),
    SELTEN(ChatColor.BLUE),
    EPISCH(ChatColor.DARK_PURPLE),
    LEGENDÄR(ChatColor.GOLD);

    public ChatColor color;

    private Rarity(ChatColor color) {
        this.color = color;
    }

    public ChatColor getChatColor() {
        return this.color;
    }

    public String getName() {
        String str = this.name().toLowerCase();
        char c = Character.toUpperCase(str.charAt(0));
        return c + str.substring(1, str.length());
    }

}
