package me.martin.main.CombatTag;

import me.martin.main.Main;
import me.martin.main.Utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;

public class CombatTag implements Listener {

    Main main;

    public CombatTag(Main main){

        this.main = main;

    }

    HashSet<Player> combatLog = new HashSet<>();

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void combatTag(EntityDamageByEntityEvent e){

        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){

            Player attacker = ((Player) e.getDamager()).getPlayer();
            Player victim = ((Player) e.getEntity()).getPlayer();

            if(!combatLog.contains(attacker)){

                combatLog.add(attacker);

                attacker.sendMessage(Utils.chatColor("&8[&e●&8] &7You have been combat tagged for &b30 seconds &7by &b" + victim.getDisplayName() + " &7."));

                new BukkitRunnable(){

                    @Override
                    public void run() {

                        combatLog.remove(attacker);

                        attacker.sendMessage(Utils.chatColor("&8[&e●&8] &7You are no longer combat tagged!"));

                    }
                }.runTaskLater(main, 30*20);

            }

            if(!combatLog.contains(victim)){

                combatLog.add(victim);

                victim.sendMessage(Utils.chatColor("&8[&e●&8] &7You have been combat tagged for &b30 seconds &7by &b" + attacker.getDisplayName() + " &7."));

                new BukkitRunnable(){

                    @Override
                    public void run() {

                        combatLog.remove(victim);

                        victim.sendMessage(Utils.chatColor("&8[&e●&8] &7You are no longer combat tagged!"));

                    }
                }.runTaskLater(main, 30*20);

            }

        }

    }

    @EventHandler
    public void combatTagCommand(PlayerCommandPreprocessEvent e){

        Player player = e.getPlayer();

        if(combatLog.contains(player)){

            e.setCancelled(true);

            player.sendMessage(Utils.chatColor("&8[&c●&8] &7You can't type commands while combat tagged!"));

        }

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){

        Player player = e.getEntity().getPlayer();

        if(combatLog.contains(player)){

            combatLog.remove(player);

            player.sendMessage(Utils.chatColor("&8[&e●&8] &7You are no longer combat tagged!"));

        }

    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){

        Player player = e.getPlayer();

        if(combatLog.contains(player)){

            player.setHealth(0.0);

            combatLog.remove(player);

        }

    }

}
