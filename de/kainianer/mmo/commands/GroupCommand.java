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

import de.kainianer.mmo.groups.Group;
import de.kainianer.mmo.groups.Group.GroupStatus;
import de.kainianer.mmo.managers.GroupManager;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author kainianer
 */
public class GroupCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (strings.length != 0) {
                if (strings[0].equalsIgnoreCase("create")) {
                    if (strings.length == 2) {
                        String name = strings[1];
                        if (GroupManager.getInstance().hasPlayerGroup(player)) {
                            player.sendMessage(ChatColor.RED + "Du bist bereits in einer Gruppe.");
                            return false;
                        }
                        if (!GroupManager.getInstance().existsGroup(name)) {
                            GroupManager.getInstance().createGroup(name, player);
                            player.sendMessage(ChatColor.GREEN + "Gruppe " + name + " wurde erfolgreich erstellt!");
                            GroupManager.getInstance().reloadGroups();
                            return true;
                        } else {
                            player.sendMessage(ChatColor.RED + "Eine Gruppe mit diesem Namen existiert bereits!");
                            return false;
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Du musst einen Gruppennamen angeben!");
                        return false;
                    }
                } else if (strings[0].equalsIgnoreCase("invite")) {
                    if (!GroupManager.getInstance().isOwner(player)) {
                        player.sendMessage(ChatColor.RED + "Du bist nicht der Leiter dieser Gruppe!");
                        return false;
                    }
                    if (strings.length == 2) {
                        String name = strings[1];
                        Player invPlayer = Bukkit.getPlayer(name);
                        if (invPlayer.getUniqueId().equals(player.getUniqueId())) {
                            player.sendMessage(ChatColor.RED + "Du kannst dich nicht selbst in eine Gruppe einladen.");
                            return false;
                        }
                        if (invPlayer != null) {
                            invPlayer.sendMessage(ChatColor.GREEN + "Du wurdest in die Gruppe " + GroupManager.getInstance().getGroupOfPlayer(player).getName() + " eingeladen.");
                            GroupManager.getInstance().getPendingInvites().put(player.getUniqueId(), GroupManager.getInstance().getGroupOfPlayer(player));
                            return true;
                        } else {
                            player.sendMessage(ChatColor.RED + "Spieler " + name + " nicht gefunden!");
                            return false;
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Du musst einen Spielernamen angeben!");
                        return false;
                    }
                } else if (strings[0].equalsIgnoreCase("kick")) {
                    if (!GroupManager.getInstance().isOwner(player)) {
                        player.sendMessage(ChatColor.RED + "Du bist nicht der Leiter dieser Gruppe.");
                        return false;
                    }
                    if (strings.length == 2) {
                        String name = strings[1];
                        Player invPlayer = Bukkit.getPlayer(name);
                        if (invPlayer.getUniqueId().equals(player.getUniqueId())) {
                            player.sendMessage(ChatColor.RED + "Du kannst dich nicht selbst aus der Gruppe entfernen.");
                            return false;
                        }
                        if (invPlayer != null) {
                            if (GroupManager.getInstance().getGroupOfPlayer(invPlayer).equals(GroupManager.getInstance().getGroupOfPlayer(player))) {
                                GroupManager.getInstance().getGroupOfPlayer(invPlayer).removePlayer(invPlayer);
                                invPlayer.sendMessage(ChatColor.RED + "Du wurdest aus der Gruppe " + GroupManager.getInstance().getGroupOfPlayer(player).getName() + " entfernt!");
                                player.sendMessage(ChatColor.GREEN + "Spieler " + invPlayer.getName() + " wurde aus der Gruppe entfernt!");
                                return true;
                            } else {
                                player.sendMessage(ChatColor.RED + "Spieler " + name + " ist nicht in deiner Gruppe!");
                                return false;
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "Spieler " + name + " nicht gefunden!");
                            return false;
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Du musst einen Spielernamen angeben!");
                        return false;
                    }
                } else if (strings[0].equalsIgnoreCase("leave")) {
                    if (!GroupManager.getInstance().hasPlayerGroup(player)) {
                        player.sendMessage(ChatColor.RED + "Du bist in keiner Gruppe!");
                        return false;
                    }
                    for (UUID id : GroupManager.getInstance().getGroupOfPlayer(player).getPlayerIds().keySet()) {
                        Bukkit.getPlayer(id).sendMessage(ChatColor.RED + "Spieler " + player.getName() + " hat die Gruppe verlassen.");
                        return true;
                    }
                    GroupManager.getInstance().getGroupOfPlayer(player).removePlayer(player);
                    player.sendMessage(ChatColor.GREEN + "Du hast die Gruppe verlassen.");
                    return true;
                } else if (strings[0].equalsIgnoreCase("accept")) {
                    if (!GroupManager.getInstance().getPendingInvites().containsKey(player.getUniqueId())) {
                        player.sendMessage(ChatColor.RED + "Du hast keine ausstehenden Einladungen.");
                        return false;
                    } else {
                        Group group = GroupManager.getInstance().getPendingInvites().get(player.getUniqueId());
                        GroupManager.getInstance().getPendingInvites().remove(player.getUniqueId());
                        for (UUID id : group.getPlayerIds().keySet()) {
                            Bukkit.getPlayer(id).sendMessage(ChatColor.GREEN + "Spieler " + player.getName() + " ist der Gruppe beigetreten.");
                        }
                        group.addPlayer(player, GroupStatus.MITGLIED);
                        return true;
                    }
                } else {
                    player.sendMessage(ChatColor.GREEN + "Befehle für Gruppen:");
                    player.sendMessage("  /group invite <player>");
                    player.sendMessage("  /group kick <player>");
                    player.sendMessage("  /group accept");
                    player.sendMessage("  /group leave");
                }
            } else {
                player.sendMessage(ChatColor.GREEN + "Befehle für Gruppen:");
                player.sendMessage("  /group invite <player>");
                player.sendMessage("  /group kick <player>");
                player.sendMessage("  /group accept");
                player.sendMessage("  /group leave");
            }
        }
        return false;
    }
}
