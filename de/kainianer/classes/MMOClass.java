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
package de.kainianer.classes;

import org.bukkit.ChatColor;

/**
 *
 * @author kainianer
 */
public enum MMOClass {

    HUNTER(ChatColor.DARK_GREEN, "Schütze", "Jäger", "Drachentöter"),
    WIZARD(ChatColor.LIGHT_PURPLE, "Novize", "Mönch", "Älstester"),
    WARRIOR(ChatColor.BLUE, "Knappe","Ritter","Krieger");
    
    private final String rank1;
    private final String rank2;
    private final String rank3;
    private final ChatColor color;
    
    private MMOClass(ChatColor color, String rank1, String rank2, String rank3) {
        this.rank1 = rank1;
        this.rank2 = rank2;
        this.rank3 = rank3;
        this.color = color;
    }
    
    public ChatColor getColor() {
        return this.color;
    }
    
    public String getRankOne() {
        return this.color + this.rank1;
    }
    public String getRankTwo() {
        return this.color + this.rank2;
    }
    public String getRankThree() {
        return this.color + this.rank3;
    }
}
