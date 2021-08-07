package me.martin.main.Scoreboards.ScoreboardUpdateEvents;

import me.martin.main.Main;
import me.martin.main.Scoreboards.SBManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class SBUpdate implements Listener {

    Main main;

    public SBUpdate(Main main){

        this.main = main;

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){

        for(Player onlinePlayers : Bukkit.getOnlinePlayers()){

            SBManager.displayScoreboard(onlinePlayers, SBManager.createScoreboard(onlinePlayers));

        }

    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){

        for(Player onlinePlayers : Bukkit.getOnlinePlayers()){

            new BukkitRunnable(){

                @Override
                public void run() {

                    SBManager.displayScoreboard(onlinePlayers, SBManager.createScoreboard(onlinePlayers));

                }
            }.runTaskLater(main, 1);

        }

    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){

        Player player = e.getEntity().getPlayer();

        new BukkitRunnable(){


            @Override
            public void run() {

                SBManager.displayScoreboard(player, SBManager.createScoreboard(player));

            }
        }.runTaskLater(main, 1);

    }

    @EventHandler
    public void onPlayerKill(EntityDeathEvent e){

        if(e.getEntity() instanceof Player && e.getEntity().getKiller() instanceof Player){

            Player killer = e.getEntity().getKiller().getPlayer();
            Player victim = ((Player) e.getEntity()).getPlayer();

            SBManager.displayScoreboard(killer, SBManager.createScoreboard(killer));
            SBManager.displayScoreboard(victim, SBManager.createScoreboard(victim));

        }

    }



}
