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

import de.kainianer.genuine.item.Stat.StatType;
import org.bukkit.ChatColor;

/**
 *
 * @author kainianer
 */
public class Stat {

    public enum StatType {

        SCHADEN,
        ZAUBERSCH,
        LEBENSREG,
        HUNGERREG,
        LEBENSRAU,
        RÜSTUNG,
        ERFAHRUNG,
        LEBEN,
        LEVEL;

    }

    private final StatType type;
    private final double value;

    public Stat(StatType type, double value) {
        this.type = type;
        this.value = value;
    }

    public String getStatName() {
        String str = this.type.name().toLowerCase();
        char c = Character.toUpperCase(str.charAt(0));
        return c + str.substring(1, str.length());
    }

    /**
     * @return the type
     */
    public StatType getType() {
        return type;
    }

    /**
     * @return the value
     */
    public double getValue() {
        return value;
    }

    public String getValueAsString() {
        switch (this.type) {
            case HUNGERREG:
                return ChatColor.BLUE + "+" + String.valueOf(this.value);
            case LEBENSREG:
                return ChatColor.GREEN + "+" + String.valueOf(this.value);
            case LEBENSRAU:
                return ChatColor.GREEN + "+" + String.valueOf(this.value);
            case LEVEL:
                return String.valueOf((int) this.getValue());
            case ERFAHRUNG:
                return String.valueOf((int) this.getValue()) + "%";
            case ZAUBERSCH:
                return String.valueOf((int) this.getValue()) + "%";
            default:
                return String.valueOf(this.value);
        }
    }
}
