package me.martin.main.Commands;

import me.martin.main.Guns.Guns;
import me.martin.main.Main;
import me.martin.main.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class GiveGuns implements CommandExecutor {

    public GiveGuns(Main main){

    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender instanceof Player){

            Player player = (Player) commandSender;

            if(args.length > 0){

                if(args[0].equalsIgnoreCase("list")){

                    player.sendMessage(" ");
                    player.sendMessage(Utils.chatColor("&7&o----------Guns List----------"));
                    player.sendMessage(Utils.chatColor(Guns.Barrett().getItemMeta().getDisplayName()) + Utils.chatColor("&7 - &7&obarrett"));
                    player.sendMessage(Utils.chatColor(Guns.Deagle().getItemMeta().getDisplayName()) + Utils.chatColor("&7 - &7&odeagle"));
                    player.sendMessage(Utils.chatColor(Guns.Spas().getItemMeta().getDisplayName()) + Utils.chatColor("&7 - &7&ospas"));
                    player.sendMessage(Utils.chatColor(Guns.GoldenBarrett().getItemMeta().getDisplayName()) + Utils.chatColor("&7 - &7&ogbar"));
                    player.sendMessage(Utils.chatColor(Guns.PinkPrecision().getItemMeta().getDisplayName() + Utils.chatColor("&7 - &7&opinky")));
                    player.sendMessage(Utils.chatColor(Guns.GoldenDeagle().getItemMeta().getDisplayName()) + Utils.chatColor("&7 - &7&ogdeagle"));
                    player.sendMessage(Utils.chatColor(Guns.Katana().getItemMeta().getDisplayName() + Utils.chatColor("&7 - &7&okatana")));
                    player.sendMessage(" ");

                }

                if(args[0].equalsIgnoreCase("barrett")){

                    player.sendMessage(Utils.chatColor("&8[&a●&8] &7You got " + Guns.Barrett().getItemMeta().getDisplayName()));

                    player.getInventory().addItem(Guns.Barrett());


                }

                if(args[0].equalsIgnoreCase("deagle")){

                    player.sendMessage(Utils.chatColor("&8[&a●&8] &7You got " + Guns.Deagle().getItemMeta().getDisplayName()));

                    player.getInventory().addItem(Guns.Deagle());

                }

                if(args[0].equalsIgnoreCase("spas")){

                    player.sendMessage(Utils.chatColor("&8[&a●&8] &7You got " + Guns.Spas().getItemMeta().getDisplayName()));

                    player.getInventory().addItem(Guns.Spas());

                }

                if(args[0].equalsIgnoreCase("gdeagle")){

                    player.sendMessage(Utils.chatColor("&8[&a●&8] &7You got " + Guns.GoldenDeagle().getItemMeta().getDisplayName()));

                    player.getInventory().addItem(Guns.GoldenDeagle());

                }

                if(args[0].equalsIgnoreCase("gbar")){

                    player.sendMessage(Utils.chatColor("&8[&a●&8] &7You got " + Guns.GoldenBarrett().getItemMeta().getDisplayName()));

                    player.getInventory().addItem(Guns.GoldenBarrett());

                }

                if(args[0].equalsIgnoreCase("katana")){

                    player.sendMessage(Utils.chatColor("&8[&a●&8] &7You got " + Guns.Katana().getItemMeta().getDisplayName()));

                    player.getInventory().addItem(Guns.Katana());

                }

                if(args[0].equalsIgnoreCase("pinky")){

                    player.sendMessage(Utils.chatColor("&8[&a●&8] &7You got " + Guns.PinkPrecision().getItemMeta().getDisplayName()));

                    player.getInventory().addItem(Guns.PinkPrecision());

                }

            }else{

                player.sendMessage(" ");
                player.sendMessage(Utils.chatColor("&c/gun <gun name> &7- &7&oGives you a desired gun!"));
                player.sendMessage(Utils.chatColor("&c/gun list &7- &7&oDisplays a list of available guns!"));
                player.sendMessage(" ");

            }

        }

        return true;
    }
}
