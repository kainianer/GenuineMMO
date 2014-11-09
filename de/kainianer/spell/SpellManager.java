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
package de.kainianer.spell;

import de.kainianer.genuine.Main;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kainianer
 */
public class SpellManager implements Runnable {

    private final List<DurableSpell> durableSpellList = new ArrayList<>();
    private final Main main;

    public SpellManager(Main main) {
        this.main = main;
    }

    public List<DurableSpell> getDurableSpellList() {
        return this.durableSpellList;
    }

    @Override
    public void run() {
        List<DurableSpell> toRemove = new ArrayList<>();
        for (DurableSpell spell : this.durableSpellList) {
            if (spell.isExpired()) {
                spell.cancel();
                toRemove.add(spell);
            } else {
                spell.increaseLived();
            }
        }
        this.durableSpellList.removeAll(toRemove);
    }

    public void addSpellToManager(DurableSpell spell) {
        this.durableSpellList.add(spell);
    }

    public static void addSpell(DurableSpell spell) {
        Main.getInstance().getSpellManager().addSpellToManager(spell);
    }

}
