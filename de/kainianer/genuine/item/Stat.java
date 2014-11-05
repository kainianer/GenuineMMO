/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
        LEVEL;

    }

    private final StatType type;
    private final int value;
    private final boolean percent;

    public Stat(StatType type, int value, boolean percent) {
        this.type = type;
        this.value = value;
        this.percent = percent;
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
    public int getValue() {
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
        }
        if (this.percent) {
            return String.valueOf(this.value) + "%";
        } else {
            return String.valueOf(this.value);
        }
    }

    /**
     * @return the percent
     */
    public boolean isPercent() {
        return percent;
    }

}
