package me.martin.main.Guns;

import de.tr7zw.nbtapi.NBTItem;
import me.martin.main.Main;
import me.martin.main.Utils.Utils;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;

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

    /*@EventHandler
    public void onGunHit(EntityDamageByEntityEvent e){

        if(e.getDamager() instanceof Player && e.getEntity() instanceof Player){

            Player damager = ((Player) e.getDamager()).getPlayer();

            Player victim = ((Player) e.getEntity()).getPlayer();

            if(damager.getItemInHand().getType().equals(Material.STONE_AXE) || damager.getItemInHand().getType().equals(Material.WOOD_AXE)){

                e.setDamage(1);

            }

        }

    }*/

    @EventHandler
    public void gunSwitch(PlayerItemHeldEvent e){

        Player player = e.getPlayer();

        if(player.getInventory().getItem(e.getNewSlot()) != null){

            NBTItem gun = new NBTItem(player.getInventory().getItem(e.getNewSlot()));

            if(!gun.hasKey("ammo")){

                PacketPlayOutChat blank = new PacketPlayOutChat(new ChatComponentText(Utils.chatColor(" ")), (byte) 2);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(blank);

            }

        }else{

            PacketPlayOutChat blank = new PacketPlayOutChat(new ChatComponentText(Utils.chatColor(" ")), (byte) 2);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(blank);

        }

    }

}
