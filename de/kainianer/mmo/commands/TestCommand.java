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
package de.kainianer.mmo.commands;

import de.kainianer.mmo.effects.NetherEffect;
import de.kainianer.mmo.item.CustomItem;
import de.kainianer.mmo.managers.ItemManager;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author kainianer
 */
public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            p.getInventory().clear();
            p.getInventory().setItem(8, new ItemStack(Material.ARROW, 64));
            // InvisUtil fac = new InvisUtil(MainMMO.getInstance());
            //fac.setGhost(p, true);
            NetherEffect effect = new NetherEffect(p);
            effect.infinite();
            effect.start();
            for (Map.Entry<String, CustomItem> entry : ItemManager.getInstance().getItemList().entrySet()) {
                p.getInventory().addItem(entry.getValue());
            }
            ItemStack stack = new ItemStack(Material.TORCH);
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName(ChatColor.DARK_GREEN + "Sumpfkraut");
            stack.setItemMeta(meta);
            p.getInventory().addItem(stack);
        }
        return false;
    }

}
