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
    LEBENSRAUB(WeaponType.WAND, 3),
    SCHWUNG(WeaponType.WAND, 3),
    //Warrior
    BLINDHEIT(WeaponType.WAND, 2),
    RÜSTUNG(WeaponType.SPEAR, 2),
    VERLANGSAMUNG(WeaponType.SPEAR, 2),
    ZERFETZEN(WeaponType.SPEAR, 3),
    RÜCKSTOß(WeaponType.SPEAR, 1),
    WEAKNESS(WeaponType.SPEAR, 1);

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
