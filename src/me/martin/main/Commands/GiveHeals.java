package me.martin.main.Commands;

import me.martin.main.Guns.Guns;
import me.martin.main.Heals.Bandages;
import me.martin.main.Heals.Heals;
import me.martin.main.Heals.Syringes;
import me.martin.main.Main;
import me.martin.main.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveHeals implements CommandExecutor {

    public GiveHeals(Main main){

    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender instanceof Player){

            Player player = (Player) commandSender;

            if(args.length > 0) {

                if(args[0].equalsIgnoreCase("list")){

                    player.sendMessage(" ");
                    player.sendMessage(Utils.chatColor("&7&o----------Heals List----------"));
                    player.sendMessage(Utils.chatColor("&dBandages &7- &7&obandage"));
                    player.sendMessage(Utils.chatColor("&dSyringes &7- &7&osyringe"));
                    player.sendMessage(Utils.chatColor("&dPainkillers &7- &7&opainkillers"));
                    player.sendMessage(" ");

                }

                if(args[0].equalsIgnoreCase("bandage")){

                    player.sendMessage(Utils.chatColor("&8[&a●&8] &7You got &2" + Integer.valueOf(args[1])
                            + Utils.chatColor("&2x " + Utils.chatColor("&dBandages"))));

                    player.getInventory().addItem(Heals.bandageWrapped(Integer.valueOf(args[1])));

                }

                if(args[0].equalsIgnoreCase("syringe")){

                    player.sendMessage(Utils.chatColor("&8[&a●&8] &7You got &2" + Integer.valueOf(args[1])
                            + Utils.chatColor("&2x " + Utils.chatColor("&dSyringes"))));

                    player.getInventory().addItem(Syringes.syringe(Integer.valueOf(args[1])));



                }

                if(args[0].equalsIgnoreCase("painkillers")){

                    player.sendMessage(Utils.chatColor("&8[&a●&8] &7You got &2" + Integer.valueOf(args[1])
                            + Utils.chatColor("&2x " + Utils.chatColor("&dPainkillers"))));

                    player.getInventory().addItem(Heals.Painkillers(Integer.valueOf(args[1])));

                }

            }else{

                player.sendMessage(" ");
                player.sendMessage(Utils.chatColor("&c/heals <item> <amount> >&7- &7&oGives you a desired item!"));
                player.sendMessage(Utils.chatColor("&c/heals list &7- &7&oShows a list of available heals!"));
                player.sendMessage(" ");

            }

        }

        return false;
    }
}
