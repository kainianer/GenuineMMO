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

import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author kainianer
 */
public class InventoryManager {

    private static InventoryManager instance = new InventoryManager();
    private HashMap<Player, ItemStack[]> inventories;
    private HashMap<Player, ItemStack[]> armors;

    private InventoryManager() {
        this.inventories = new HashMap();
        this.armors = new HashMap();
    }

    public void saveInventoryAndArmor(Player player) {
        this.inventories.put(player, player.getInventory().getContents());
        this.armors.put(player, player.getInventory().getArmorContents());
    }

    public ItemStack[] loadInventory(Player player) {
        return this.inventories.get(player);
    }

    public ItemStack[] loadArmor(Player player) {
        return this.armors.get(player);
    }

    public void removeInventoryAndArmor(Player player) {
        this.inventories.remove(player);
        this.armors.remove(player);
    }

    public static InventoryManager getInstance() {
        return instance;
    }
}
