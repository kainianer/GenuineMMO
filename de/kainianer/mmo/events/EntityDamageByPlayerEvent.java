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
package de.kainianer.mmo.events;

import de.kainianer.mmo.entity.MMOPlayer;
import de.kainianer.mmo.item.Weapon;
import de.kainianer.mmo.util.ItemUtil;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author kainianer
 */
public class EntityDamageByPlayerEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final MMOPlayer mplayer;
    private final Player player;
    private final LivingEntity entity;
    private final Weapon weapon;

    public EntityDamageByPlayerEvent(Player player, LivingEntity entity, ItemStack stack) {
        this.player = player;
        this.mplayer = MMOPlayer.wrapPlayer(this.player);
        this.entity = entity;
        this.weapon = ItemUtil.getCustomWeaponItem(stack);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * @return the mplayer
     */
    public MMOPlayer getMplayer() {
        return mplayer;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @return the entity
     */
    public LivingEntity getEntity() {
        return entity;
    }

    /**
     * @return the weapon
     */
    public Weapon getWeapon() {
        return weapon;
    }
}
