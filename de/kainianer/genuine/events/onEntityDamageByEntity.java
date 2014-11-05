/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.kainianer.genuine.events;

import de.kainianer.genuine.Main;
import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.EffectType;
import de.slikey.effectlib.effect.LineEffect;
import de.slikey.effectlib.util.ParticleEffect;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 *
 * @author kainianer
 */
public class onEntityDamageByEntity implements Listener {

    @EventHandler
    public void onEntityDamageLivingEntity(EntityDamageByEntityEvent event) {
        LivingEntity target = (LivingEntity) event.getEntity();
        if (event.getDamager() instanceof Projectile) {
            Projectile proj = (Projectile) event.getDamager();
            if (proj instanceof Arrow) {
                Arrow ar = (Arrow) proj;
                if (ar.getShooter() instanceof Player) {
                    Player player = (Player) ar.getShooter();
                    if (!target.hasPotionEffect(PotionEffectType.WITHER)) {
                        target.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 20 * 10, 1, true));
                        EffectManager em = new EffectManager(Main.getEffectLib());
                        LineEffect effect = new LineEffect(em);
                        effect.type = EffectType.REPEATING;
                        effect.particle = ParticleEffect.RED_DUST;
                        effect.particles = 16;
                        effect.period = 1;
                        effect.iterations = 200;
                        effect.setEntity(player);
                        effect.setTargetEntity(target);
                        effect.start();
                    }

                }
            }
        }
    }

}
