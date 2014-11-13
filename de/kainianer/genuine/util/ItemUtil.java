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
package de.kainianer.genuine.util;

import de.kainianer.genuine.MainMMO;
import de.kainianer.genuine.item.Armor;
import de.kainianer.genuine.item.Gem;
import de.kainianer.genuine.item.Weapon;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author kainianer
 */
public class ItemUtil {

    public static boolean verifyCustomItem(ItemStack item) {
        if (item == null) {
            return false;
        }
        if (item.hasItemMeta()) {
            if (item.getItemMeta().hasDisplayName()) {
                return MainMMO.getInstance().getItemList().containsKey(item.getItemMeta().getDisplayName());
            }
        }
        return false;
    }

    public static Weapon getCustomWeaponItem(ItemStack stack) {
        String str = "";
        if (stack != null && stack.hasItemMeta()) {
            if (stack.getItemMeta().hasDisplayName()) {
                str = stack.getItemMeta().getDisplayName();

                if (MainMMO.getInstance().getItemList().containsKey(str) && str != "") {
                    if (MainMMO.getInstance().getItemList().get(str) instanceof Weapon) {
                        return (Weapon) MainMMO.getInstance().getItemList().get(str);
                    }
                }
            }
        }
        return null;
    }

    public static Armor getCustomArmorItem(ItemStack stack) {
        String str = "";
        if (stack != null && stack.hasItemMeta()) {
            if (stack.getItemMeta().hasDisplayName()) {
                str = stack.getItemMeta().getDisplayName();

                if (MainMMO.getInstance().getItemList().containsKey(str) && str != "") {
                    if (MainMMO.getInstance().getItemList().get(str) instanceof Armor) {
                        return (Armor) MainMMO.getInstance().getItemList().get(str);
                    }
                }
            }
        }
        return null;
    }

    public static Gem getCustomGemItem(ItemStack stack) {
        String str = "";
        if (stack != null && stack.hasItemMeta()) {
            if (stack.getItemMeta().hasDisplayName()) {
                str = stack.getItemMeta().getDisplayName();

                if (MainMMO.getInstance().getItemList().containsKey(str) && str != "") {
                    if (MainMMO.getInstance().getItemList().get(str) instanceof Gem) {
                        return (Gem) MainMMO.getInstance().getItemList().get(str);
                    }
                }
            }
        }
        return null;
    }

}
