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
package de.kainianer.mmo.groups;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.entity.Player;

/**
 *
 * @author kainianer
 */
public class Group {

    private final String name;
    private final int id;
    private final Map<UUID, GroupStatus> players;

    public enum GroupStatus {

        LEITER,
        PRÜFLING,
        MITGLIED;

    }

    public Group(int id, String name) {
        this.name = name;
        this.id = id;
        this.players = new HashMap<>();
    }

    public Group(int id, String name, Map<UUID, GroupStatus> map) {
        this.name = name;
        this.id = id;
        this.players = map;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the players
     */
    public Map<UUID, GroupStatus> getPlayerIds() {
        return players;
    }

    public void removePlayer(Player player) {
        this.getPlayerIds().remove(player.getUniqueId());
    }

    public void addPlayer(Player player, GroupStatus status) {
        this.getPlayerIds().put(player.getUniqueId(), status);
    }

    public boolean containsPlayer(Player player) {
        return this.getPlayerIds().containsKey(player.getUniqueId());
    }
}
