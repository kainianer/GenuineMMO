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
package de.kainianer.mmo.eventListener;

import de.kainianer.mmo.classes.MMOClass;
import de.kainianer.mmo.main.Main;
import de.kainianer.mmo.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 *
 * @author kainianer
 */
public class onPlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!PlayerManager.getInstance().existsPlayer(event.getPlayer())) {
            PlayerManager.getInstance().addPlayer(event.getPlayer(), MMOClass.HUNTER);
            Player player = event.getPlayer();
            player.sendMessage(ChatColor.WHITE + "Herzlich willkommen " + ChatColor.GREEN + event.getPlayer().getName() + ChatColor.WHITE + " auf Utopia!");
            player.sendMessage("");
            player.sendMessage(ChatColor.WHITE + "Version: " + ChatColor.GRAY + Main.getInstance().getDescription().getVersion());
            player.sendMessage(ChatColor.WHITE + "Webseite: " + ChatColor.GRAY + "http://www.utopia.net/");
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!event.getPlayer().getUniqueId().equals(player.getUniqueId())) {
                player.sendMessage(ChatColor.GRAY + ">> " + event.getPlayer().getName());
            }
        }

        event.setJoinMessage("");
        PlayerManager.getInstance().loadPlayer(event.getPlayer());
    }
}
