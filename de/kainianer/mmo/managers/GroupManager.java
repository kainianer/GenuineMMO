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

import de.kainianer.mmo.groups.Group;
import de.kainianer.mmo.groups.Group.GroupStatus;
import de.kainianer.mmo.util.DatabaseUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.entity.Player;

/**
 *
 * @author kainianer
 */
public class GroupManager {

    public static GroupManager instance = new GroupManager();
    private Map<Integer, Group> groups;
    private Map<UUID, Group> pendingInvites;

    public GroupManager() {
        this.pendingInvites = new HashMap<>();
        this.groups = new HashMap<>();
        try {
            this.loadGroups();
        } catch (SQLException ex) {
            Logger.getLogger(GroupManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadGroups() throws SQLException {
        ResultSet set = DatabaseUtil.getResultSet("SELECT * FROM guilds");
        while (set.next()) {
            this.groups.put(set.getInt(1), new Group(set.getInt(1), set.getString(2), this.getPlayersOfGroup(set.getInt(1))));
        }
    }

    public Map<Integer, Group> getGroups() {
        return this.groups;
    }

    public static GroupManager getInstance() {
        return instance;
    }

    public void createGroup(String name, Player leader) {
        try {
            DatabaseUtil.executeQuery("INSERT INTO guilds (name, leaderId) VALUES('" + name + "','" + leader.getUniqueId() + "')");
            ResultSet set = DatabaseUtil.getResultSet("SELECT MAX(id) FROM guilds");
            set.next();
            int i = set.getInt(1);
            DatabaseUtil.executeQuery("UPDATE player SET gid='" + i + "', groupStatus='" + GroupStatus.LEITER + "' WHERE id='" + leader.getUniqueId() + "'");
        } catch (SQLException ex) {
            Logger.getLogger(GroupManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean existsGroup(String name) {
        try {
            int rows = DatabaseUtil.countRows("SELECT COUNT(*) FROM guilds WHERE name='" + name + "'");
            return rows != 0;
        } catch (SQLException ex) {
            Logger.getLogger(PlayerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Group getGroupOfPlayer(Player player) {
        for (Group g : this.getGroups().values()) {
            if (g.containsPlayer(player)) {
                return g;
            }
        }
        return null;
    }

    public boolean hasPlayerGroup(Player player) {
        return this.getGroupOfPlayer(player) != null;
    }

    public void reloadGroups() {
        try {
            this.loadGroups();
        } catch (SQLException ex) {
            Logger.getLogger(GroupManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Map<UUID, GroupStatus> getPlayersOfGroup(int id) throws SQLException {
        ResultSet set = DatabaseUtil.getResultSet("SELECT id, groupStatus FROM player WHERE gid='" + id + "'");
        Map<UUID, GroupStatus> players = new HashMap<>();
        while (set.next()) {
            players.put(UUID.fromString(set.getString(1)), GroupStatus.valueOf(set.getString(2)));
        }
        return players;
    }

    public boolean isOwner(Player player) {
        return this.getGroupOfPlayer(player).getPlayerIds().get(player.getUniqueId()).equals(GroupStatus.LEITER);
    }

    public Map<UUID, Group> getPendingInvites() {
        return this.pendingInvites;
    }

}
