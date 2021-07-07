package me.martin.main.Guns;

import me.martin.main.Main;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class GunUtils implements Listener {

    Main main;

    public GunUtils(Main main){

        this.main = main;

    }

    @EventHandler
    public void removeBullets(ProjectileHitEvent e){

        if(e.getEntity() instanceof Arrow){

            Arrow bullet = (Arrow) e.getEntity();

            bullet.remove();

        }

    }

    @EventHandler
    public void onGunHit(EntityDamageByEntityEvent e){

        if(e.getDamager() instanceof Player && e.getEntity() instanceof Player){

            Player damager = ((Player) e.getDamager()).getPlayer();

            if(damager.getItemInHand().getType().equals(Material.STONE_AXE) || damager.getItemInHand().getType().equals(Material.WOOD_AXE)){

                e.setDamage(1);

            }

        }

    }

}
