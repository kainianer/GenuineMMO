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
package de.kainianer.mmo.managers;

import de.kainianer.mmo.spell.Aura;
import de.kainianer.mmo.spell.DurableSpell;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.bukkit.entity.Player;

/**
 *
 * @author kainianer
 */
public class SpellManager implements Runnable {

    public static SpellManager instance = new SpellManager();

    private List<DurableSpell> durableSpellList;
    private List<DurableSpell> puffer;
    private Map<UUID, List<Aura>> auraList;

    public SpellManager() {
        this.durableSpellList = new ArrayList<>();
        this.puffer = new ArrayList<>();
        this.auraList = new HashMap<>();
    }

    public List<DurableSpell> getDurableSpellList() {
        return this.durableSpellList;
    }

    @Override
    public void run() {
        List<DurableSpell> remove = new ArrayList<>();
        for (DurableSpell spell : this.durableSpellList) {
            if (spell.isExpired()) {
                spell.cancel();
                remove.add(spell);
            } else {
                spell.increaseLived();
            }
        }
        this.durableSpellList.removeAll(remove);
        this.durableSpellList.addAll(this.puffer);
        this.puffer.clear();
    }

    public void addSpellToManager(DurableSpell spell) {
        this.puffer.add(spell);
    }

    public static void addSpell(DurableSpell spell) {
        SpellManager.getInstance().addSpellToManager(spell);
    }

    public static SpellManager getInstance() {
        return instance;
    }

    public Map<UUID, List<Aura>> getAuraMap() {
        return this.auraList;
    }

    public static boolean hasAura(Player player, Aura aura) {
        if (SpellManager.getInstance().getAuraMap().containsKey(player.getUniqueId())) {
            for (Aura au : SpellManager.getInstance().getAuraMap().get(player.getUniqueId())) {
                if (au.getType().equals(aura.getType())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void addAura(Player player, Aura aura) {
        if (SpellManager.getInstance().getAuraMap().containsKey(player.getUniqueId())) {
            List<Aura> auras = SpellManager.getInstance().getAuraMap().get(player.getUniqueId());
            auras.add(aura);
            SpellManager.getInstance().getAuraMap().remove(player.getUniqueId());
            SpellManager.getInstance().getAuraMap().put(player.getUniqueId(), auras);
        } else {
            List<Aura> auras = new ArrayList<>();
            auras.add(aura);
            SpellManager.getInstance().getAuraMap().put(player.getUniqueId(), auras);
        }
    }
}
