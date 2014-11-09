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

import org.bukkit.Material;

/**
 *
 * @author kainianer
 */
public enum BonusSpell {

    //Hunter
    FEUERPFEILE(WeaponType.BOW, 1),
    UNENDLICHKEIT(WeaponType.BOW, 0),
    GIFTPFEIL(WeaponType.BOW, 1),
    SPLITTERPFEIL(WeaponType.BOW, 3),
    EXPLOSIVSCHUSS(WeaponType.BOW, 3),
    FESSELN(WeaponType.BOW, 2),
    //Monk
    HEILUNG(WeaponType.WAND, 3),
    ABSORB(WeaponType.WAND, 5),
    FEUERBALL(WeaponType.WAND, 1),
    EISLANZE(WeaponType.WAND, 1),
    AUSSAUGEN(WeaponType.WAND, 3),
    SCHWUNG(WeaponType.WAND, 3),
    //Warrior
    BLINDHEIT(WeaponType.SPEAR, 2),
    VERWIRRUNG(WeaponType.SPEAR, 2),
    VERLANGSAMUNG(WeaponType.SPEAR, 2),
    LEBENSRAUB(WeaponType.SPEAR, 3),
    RÜCKSTOß(WeaponType.SPEAR, 2),
    SCHWÄCHE(WeaponType.SPEAR, 1);

    private final WeaponType type;
    private final int hunger;

    private BonusSpell(WeaponType type, int hunger) {
        this.type = type;
        this.hunger = hunger;
    }

    public WeaponType canBeAppliedTo() {
        return this.type;
    }

    public int getHungerCost() {
        return this.hunger;
    }

    public boolean canEnchant(Material mat) {
        return mat == this.canBeAppliedTo().getMaterial();
    }

    public boolean canEnchant(WeaponType type) {
        return this.type == type;
    }

    public String getName() {
        String str = this.name().toLowerCase();
        char c = Character.toUpperCase(str.charAt(0));
        return c + str.substring(1, str.length());
    }

}
