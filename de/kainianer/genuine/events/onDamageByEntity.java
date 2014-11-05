/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kainianer.genuine.events;

import de.kainianer.ui.TargetBars;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 *
 * @author kainianer
 */
public class onDamageByEntity implements Listener {

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof LivingEntity) {
            if (event.getDamager() instanceof Projectile) {
                if (((Projectile) event.getDamager()).getShooter() instanceof Player) {
                    TargetBars.getInstance().setTargetOfPlayer(((Player) ((Projectile) event.getDamager()).getShooter()), (LivingEntity) event.getEntity());
                }
            } else if (event.getDamager() instanceof Player) {
                TargetBars.getInstance().setTargetOfPlayer((Player) event.getDamager(), (LivingEntity) event.getEntity());
            }
        }
    }

}
