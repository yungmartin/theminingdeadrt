package me.martin.main.Deaths;

import me.martin.main.Main;

import me.martin.main.Utils.Utils;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;

public class Deaths implements Listener {

    Main main;

    public Deaths(Main main){

        this.main = main;

    }

    HashSet<Player> respawning = new HashSet<>();

    @EventHandler
    public void onDeath(PlayerDeathEvent e){

        e.getDrops().clear();

        e.setDeathMessage(null);

        if(e.getEntity() instanceof Player && e.getEntity().getKiller() instanceof Player) {

            Player victim = e.getEntity().getPlayer();

            Player killer = e.getEntity().getKiller().getPlayer();

            String killerName = killer.getDisplayName();

            Location respawnLocation = new Location(Bukkit.getWorld(main.getConfig().getString("SpawnLocation.World")), main.getConfig().getDouble("SpawnLocation.X")
                    , main.getConfig().getDouble("SpawnLocation.Y"), main.getConfig().getDouble("SpawnLocation.Z")
                    , (float) main.getConfig().getDouble("SpawnLocation.Pitch"), (float) main.getConfig().getDouble("SpawnLocation.Yaw"));


            if(victim.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK
                    || victim.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.PROJECTILE){

                victim.sendMessage(Utils.chatColor("&8[&e●&8] &7You got killed by &e" + killerName));

                PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
                        IChatBaseComponent.ChatSerializer.a("{\"text\":\"§4You died!\"}"), 100, 10, 20);

                PacketPlayOutTitle subtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
                        IChatBaseComponent.ChatSerializer.a("{\"text\":\"§7Killed by §e" + killerName +  " §8(§c" + String.format("%.1f", killer.getHealth()) + "§c❤§8)" + "\"}"), 100, 10, 20);

                killer.sendMessage(Utils.chatColor("&8[&e●&8] &7You killed &e" + victim.getDisplayName()));


                ((CraftPlayer)victim).getHandle().playerConnection.sendPacket(title);
                ((CraftPlayer)victim).getHandle().playerConnection.sendPacket(subtitle);

                respawning.add(victim);
                victim.setHealth(victim.getHealth() + 1);
                victim.setGameMode(GameMode.SPECTATOR);

                new BukkitRunnable(){


                    @Override
                    public void run() {

                        victim.setHealth(20.0);
                        victim.setFoodLevel(20);
                        victim.spigot().respawn();
                        victim.teleport(respawnLocation);
                        victim.setGameMode(GameMode.ADVENTURE);
                        victim.setFlySpeed(0.1f);
                        Bukkit.dispatchCommand(victim, "kit pvp");

                        for(PotionEffect effect : victim.getActivePotionEffects()){

                            victim.removePotionEffect(effect.getType());

                        }

                        respawning.remove(victim);

                    }
                }.runTaskLater(main, 100);


            }

        }

    }

    @EventHandler
    public void disableFlying(PlayerMoveEvent e){

        Player player = e.getPlayer();

        if(respawning.contains(player)){

            player.setFlySpeed(0);

        }else{

            e.setCancelled(false);

        }

    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){

        e.setQuitMessage(null);

        Player player = e.getPlayer();

        Location respawnLocation = new Location(Bukkit.getWorld(main.getConfig().getString("SpawnLocation.World")), main.getConfig().getDouble("SpawnLocation.X")
                , main.getConfig().getDouble("SpawnLocation.Y"), main.getConfig().getDouble("SpawnLocation.Z")
                , (float) main.getConfig().getDouble("SpawnLocation.Pitch"), (float) main.getConfig().getDouble("SpawnLocation.Yaw"));

        player.teleport(respawnLocation);

        respawning.remove(player);

        player.setGameMode(GameMode.ADVENTURE);

        player.setHealth(20.0);
        player.setFoodLevel(20);



    }

}
