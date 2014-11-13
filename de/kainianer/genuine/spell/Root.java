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

import de.kainianer.genuine.util.SpellManager;
import de.kainianer.genuine.item.BonusSpell;
import de.kainianer.genuine.util.SpellUtil;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 *
 * @author kainianer
 */
public class Root extends DurableSpell {

    private List<Block> blockList;
    private final List<Material> oldMaterial = new ArrayList<>();

    private Root(int length, Location loc, Player player) {
        super(length, 0, player);
        this.blockList = SpellUtil.getBlocksInRadius(loc, 1);
        for (int i = 0; i < blockList.size(); i++) {
            oldMaterial.add(i, blockList.get(i).getType());
        }
    }

    @Override
    public void start() {
        for (Block block : this.blockList) {
            block.setType(Material.WEB);
        }
        SpellManager.addSpell(this);
    }

    @Override
    public void cancel() {
        for (int i = 0; i < this.blockList.size(); i++) {
            this.blockList.get(i).setType(this.oldMaterial.get(i));
        }
    }

    public static void perform(Player player, Location loc) {
        if (Spell.canPerform(player, BonusSpell.FESSELN)) {
            Root root = new Root(2, loc, player);
            root.start();
            Spell.removeHungerFromPlayer(player, BonusSpell.FESSELN);
        }
    }

    @Override
    public void tick() {
    }

}
