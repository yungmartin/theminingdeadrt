package me.martin.main.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SzGUI implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){

        if(e.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Safezones")){
            e.setCancelled(true);

            float pitch = (float) -0.6;
            float yaw = (float) 179.7;
            Player p = (Player) e.getWhoClicked();
            Location berx = new Location(Bukkit.getWorld("world"),1678.564, 74, 574.605, yaw, pitch);
            Location school = new Location(Bukkit.getWorld("world"), 1268.461, 86, 1268.504, (float) 89.6, (float) -1.1);

            switch(e.getCurrentItem().getType()){
                case CLAY:
                    p.teleport(berx);
                    p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + ChatColor.BOLD + "●" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY + "You've arrived at " + ChatColor.DARK_GREEN + "Berxley Safehaven" + ChatColor.GRAY + ".");
                    break;
                case WOOD_SWORD:
                    break;
                case PUMPKIN_SEEDS:
                    break;
                case BRICK:
                    p.teleport(school);
                    p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + ChatColor.BOLD + "●" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY + "You've arrived at " + ChatColor.DARK_GREEN + "School Safezone" + ChatColor.GRAY + ".");
            }

        }
    }

}
