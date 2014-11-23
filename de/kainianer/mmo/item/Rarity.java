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

import org.bukkit.ChatColor;

/**
 *
 * @author kainianer
 */
public enum Rarity {

    HÄUFIG(ChatColor.GRAY, 0),
    NORMAL(ChatColor.WHITE, 0),
    SELTEN(ChatColor.BLUE, 1),
    EPISCH(ChatColor.DARK_PURPLE, 2),
    LEGENDÄR(ChatColor.GOLD, 3);

    public ChatColor color;
    public int enchantMax;
    
    private Rarity(ChatColor color, int enchantMax) {
        this.color = color;
        this.enchantMax = enchantMax;
    }

    public ChatColor getChatColor() {
        return this.color;
    }

    public String getName() {
        String str = this.name().toLowerCase();
        char c = Character.toUpperCase(str.charAt(0));
        return c + str.substring(1, str.length());
    }

    public static double getDropChancePercent(Rarity rar) {
        switch (rar) {
            case HÄUFIG:
                return 0.15;
            case NORMAL:
                return 0.08;
            case SELTEN:
                return 0.02;
            case EPISCH:
                return 0.002;
            case LEGENDÄR:
                return 0.00005;
            default:
                return 0;
        }
    }
    
    public int getMaxEnchantments() {
        return this.enchantMax;
    }
}
