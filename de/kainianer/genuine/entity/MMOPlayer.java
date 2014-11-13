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
package de.kainianer.genuine.entity;

import de.kainianer.genuine.classes.MMOClass;
import de.kainianer.genuine.item.Armor;
import de.kainianer.genuine.item.Stat.StatType;
import de.kainianer.genuine.util.ItemUtil;
import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author kainianer
 */
public class MMOPlayer {

    private Player player;
    private String playerName;
    private UUID id;
    private MMOClass mclass;
    private int totalExp;

    public MMOPlayer(Player player) {
        this.player = player;
        this.id = player.getUniqueId();
        this.playerName = player.getDisplayName();
        this.mclass = MMOClass.HUNTER;
        this.totalExp = player.getTotalExperience();
    }

    /**
     * @return the playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * @return the mclass
     */
    public MMOClass getMclass() {
        return mclass;
    }

    /**
     * @return the totalExp
     */
    public int getTotalExp() {
        return totalExp;
    }

    /**
     * @return the Level
     */
    public int getLevel() {
        return this.player.getLevel();
    }

    public int getBonusHealReg() {
        int i = 0;
        for (ItemStack stack : this.player.getInventory().getArmorContents()) {
            if (ItemUtil.verifyCustomItem(stack)) {
                Armor armor = ItemUtil.getCustomArmorItem(stack);
                i += armor.getValueOf(StatType.LEBENSREG);
            }
        }
        return i;
    }

    public int getBonusManaReg() {
        int i = 0;
        for (ItemStack stack : this.player.getInventory().getArmorContents()) {
            if (ItemUtil.verifyCustomItem(stack)) {
                Armor armor = ItemUtil.getCustomArmorItem(stack);
                i += armor.getValueOf(StatType.HUNGERREG);
            }
        }
        return i;
    }

    public int getBonusHealth() {
        int i = 0;
        for (ItemStack stack : this.player.getInventory().getArmorContents()) {
            if (ItemUtil.verifyCustomItem(stack)) {
                Armor armor = ItemUtil.getCustomArmorItem(stack);
                i += armor.getValueOf(StatType.LEBEN);
            }
        }
        return i;
    }

    public int getBonusExperience() {
        int i = 0;
        for (ItemStack stack : this.player.getInventory().getArmorContents()) {
            if (ItemUtil.verifyCustomItem(stack)) {
                Armor armor = ItemUtil.getCustomArmorItem(stack);
                i += armor.getValueOf(StatType.ERFAHRUNG);
            }
        }
        return i;

    }

    public static MMOPlayer wrapPlayer(Player player) {
        return new MMOPlayer(player);
    }

    public void updateMaxHelath() {
        this.player.setMaxHealth(this.player.getMaxHealth() + (double) this.getBonusHealth());
    }

    public double getBonusDamage() {
        int i = 0;
        for (ItemStack stack : this.player.getInventory().getArmorContents()) {
            if (ItemUtil.verifyCustomItem(stack)) {
                Armor armor = ItemUtil.getCustomArmorItem(stack);
                i += armor.getValueOf(StatType.ZAUBERSCH);
            }
        }
        return 1 + (i / 100);

    }

}
