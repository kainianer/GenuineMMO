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

import de.kainianer.mmo.spell.Spell;
import de.kainianer.mmo.spells.Ansturm;
import de.kainianer.mmo.spells.Aussaugen;
import de.kainianer.mmo.spells.Feuerpfeil;
import de.kainianer.mmo.spells.Giftaura;
import de.kainianer.mmo.spells.Giftpfeil;
import de.kainianer.mmo.spells.Hochwerfen;
import de.kainianer.mmo.spells.Rueckstoss;
import de.kainianer.mmo.spells.Splitterpfeil;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

/**
 *
 * @author kainianer
 */
public enum BonusSpell {

    SPLITTERPFEIL(WeaponType.BOW, 2, new Splitterpfeil()),
    FEUERPFEIL(WeaponType.BOW, 0, new Feuerpfeil()),
    GIFTPFEIL(WeaponType.BOW, 0, new Giftpfeil()),
    
    HOCHWERFEN(WeaponType.WAND, 1, new Hochwerfen()),
    AUSSAUGEN(WeaponType.WAND, 2, new Aussaugen()),
    GIFTAURA(WeaponType.WAND, 0, new Giftaura()),
    
    RUECKSTOSS(WeaponType.SPEAR, 0, new Rueckstoss()), 
    ANSTURM(WeaponType.SPEAR, 2, new Ansturm());

    private final WeaponType type;
    private final int hunger;
    private Spell spell;

    private BonusSpell(WeaponType type, int hunger, Spell spell) {
        this.type = type;
        this.hunger = hunger;
        this.spell = spell;
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

    public void perform(Player player, Entity entity, Weapon weapon) {
        this.spell.perform(player, entity, weapon, this);
    }

}
