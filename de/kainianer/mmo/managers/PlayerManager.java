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

import de.kainianer.mmo.classes.MMOClass;
import de.kainianer.mmo.entity.MMOPlayer;
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
public class PlayerManager {

    private static PlayerManager instance = new PlayerManager();
    private Map<UUID, MMOPlayer> player;

    public PlayerManager() {
        this.player = new HashMap<>();
    }

    public static PlayerManager getInstance() {
        return instance;
    }

    public boolean existsPlayer(Player player) {
        UUID id = player.getUniqueId();
        try {
            int rows = DatabaseUtil.countRows("SELECT COUNT(*) FROM player WHERE id='" + id + "'");
            return rows != 0;
        } catch (SQLException ex) {
            Logger.getLogger(PlayerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * @return the player
     */
    public Map<UUID, MMOPlayer> getPlayer() {
        return player;
    }

    public void addPlayer(Player player, MMOClass mmoClass) {
        try {
            DatabaseUtil.executeQuery("INSERT INTO player VALUES('" + player.getUniqueId() + "','" + player.getName() + "','" + mmoClass.name() + "', 0,'')");
        } catch (SQLException ex) {
            Logger.getLogger(PlayerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadPlayer(Player player) {
        MMOPlayer mplayer = new MMOPlayer(player);
        this.player.put(player.getUniqueId(), mplayer);
    }

    public MMOClass getPlayerClass(Player player) {
        UUID id = player.getUniqueId();
        ResultSet set = null;
        MMOClass cl = null;
        try {
            set = DatabaseUtil.getResultSet("SELECT class FROM player WHERE id='" + id + "'");
            set.next();
            cl = MMOClass.valueOf(set.getString(1));
            set.close();
        } catch (SQLException ex) {
            Logger.getLogger(PlayerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cl;
    }

    public void unloadPlayer(Player player) {
        this.getPlayer().remove(player.getUniqueId());
    }

}
