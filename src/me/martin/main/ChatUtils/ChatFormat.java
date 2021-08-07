package me.martin.main.ChatUtils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormat implements Listener {

    @EventHandler
    public void chat(AsyncPlayerChatEvent e){

        Player player = e.getPlayer();

        if (player.hasPermission("*")) {
            e.setFormat(ChatColor.translateAlternateColorCodes('&', "&a&lOwner &a" + e.getPlayer().getDisplayName() + "&8»&f " + e.getMessage()));
        }else if(player.hasPermission("default")){
            e.setFormat(ChatColor.translateAlternateColorCodes('&', "&7" + e.getPlayer().getDisplayName() + "&8»&f " + e.getMessage()));
        }else if(player.hasPermission("mythical")){
            e.setFormat(ChatColor.translateAlternateColorCodes('&', "&bMythical &7" + e.getPlayer().getDisplayName() + "&8»&f " + e.getMessage()));
        }

    }

}
