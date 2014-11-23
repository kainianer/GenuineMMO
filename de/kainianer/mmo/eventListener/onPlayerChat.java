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

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import de.kainianer.mmo.classes.MMOClass;
import de.kainianer.mmo.entity.MMOPlayer;
import de.kainianer.mmo.groups.Group;
import de.kainianer.mmo.main.Main;
import de.kainianer.mmo.managers.GroupManager;
import de.kainianer.mmo.util.RegionUtil;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 *
 * @author kainianer
 */
public class onPlayerChat implements Listener {
    
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        String[] amess = message.split(" ");
        if (message.charAt(0) == '@') {
            if ("@g".equals(amess[0]) || "@group".equals(amess[0])) {
                if (!GroupManager.getInstance().hasPlayerGroup(event.getPlayer())) {
                    event.getPlayer().sendMessage(ChatColor.RED + "Leider bist du in keiner Gruppe.");
                    event.setCancelled(true);
                } else {
                    Group g = GroupManager.getInstance().getGroupOfPlayer(event.getPlayer());
                    for (UUID id : g.getPlayerIds().keySet()) {
                        Player player = Bukkit.getPlayer(id);
                        message = message.substring(amess[0].length(), message.length());
                        player.sendMessage(ChatColor.GOLD + "[@G] " + ChatColor.WHITE + player.getName() + ":" + message);
                    }
                }
            }
            event.setCancelled(true);
        } else {
            Player player = event.getPlayer();
            MMOClass mclass = MMOPlayer.wrapPlayer(event.getPlayer()).getMclass();
            String abb = mclass.getColor() + "[" + mclass.getAbbrevation() + "] ";
            String level = ChatColor.GOLD + "[" + ChatColor.WHITE + event.getPlayer().getLevel() + ChatColor.GOLD + "] ";
            message = abb + level + ChatColor.WHITE + event.getPlayer().getName() + ": " + message;
            for (ProtectedRegion rg : Main.getInstance().getWorldGuard().getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation())) {
                for (LivingEntity ent : RegionUtil.getEntitiesInRegion(rg, player.getWorld())) {
                    if (ent instanceof Player) {
                        Player receiver = (Player) ent;
                        receiver.sendMessage(ChatColor.AQUA + "[" + rg.getId() + "] " + message);
                    }
                }
            }
            event.setCancelled(true);
        }
    }
}
