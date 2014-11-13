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
package de.kainianer.genuine.spell;

import de.kainianer.genuine.entity.MMOPlayer;
import de.kainianer.genuine.item.BonusSpell;
import org.bukkit.entity.Player;

/**
 *
 * @author kainianer
 */
public abstract class Spell {

    private final MMOPlayer player;

    public Spell(Player player) {
        this.player = MMOPlayer.wrapPlayer(player);
    }

    public static void removeHungerFromPlayer(Player player, BonusSpell spell) {
        player.setFoodLevel(player.getFoodLevel() - spell.getHungerCost());
    }

    public static boolean canPerform(Player player, BonusSpell spell) {
        return player.getFoodLevel() >= spell.getHungerCost();
    }

    public double getMultiplier() {
        return this.player.getBonusDamage();
    }

}
