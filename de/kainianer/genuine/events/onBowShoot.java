/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kainianer.genuine.events;

import de.kainianer.genuine.item.BonusSpell;
import de.kainianer.genuine.item.Weapon;
import de.slikey.effectlib.util.VectorUtils;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.projectiles.ProjectileSource;

/**
 *
 * @author kainianer
 */
public class onBowShoot implements Listener {

    @EventHandler
    public void onBowShoot(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            /* Player player = (Player) event.getEntity();
             Projectile proj = (Projectile) event.getProjectile();
             player.launchProjectile(Arrow.class, VectorUtils.rotateAroundAxisY(proj.getVelocity().clone(), -.01f));
             player.launchProjectile(Arrow.class, VectorUtils.rotateAroundAxisY(proj.getVelocity().clone(), 0.1f));
             WitherSkull skull = player.launchProjectile(WitherSkull.class);
             skull.setShooter((ProjectileSource) player);
             skull.setVelocity(player.getEyeLocation().getDirection().multiply(5));
             skull.setYield(0f);
             skull.setCharged(true);
             skull.setFireTicks(0);
             */
        }
    }

}
