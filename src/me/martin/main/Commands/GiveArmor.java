package me.martin.main.Commands;

import me.martin.main.Armor.Armor;
import me.martin.main.Main;
import me.martin.main.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveArmor implements CommandExecutor {

    public GiveArmor(Main main){



    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender instanceof Player){

            Player player = (Player) commandSender;

            if(args.length > 0){

                if(args[0].equalsIgnoreCase("list")){

                    player.sendMessage(" ");
                    player.sendMessage(Utils.chatColor("&7&o----------Armor List----------"));
                    player.sendMessage(Utils.chatColor(Armor.JuggHelm().getItemMeta().getDisplayName()) + Utils.chatColor("&7 - &7&ojugghelm"));
                    player.sendMessage(Utils.chatColor(Armor.JuggChestplate().getItemMeta().getDisplayName()) + Utils.chatColor("&7 - &7&ojuggchestplate"));
                    player.sendMessage(Utils.chatColor(Armor.JuggPants().getItemMeta().getDisplayName()) + Utils.chatColor("&7 - &7&ojuggpants"));
                    player.sendMessage(Utils.chatColor(Armor.JuggBoots().getItemMeta().getDisplayName()) + Utils.chatColor("&7 - &7&ojuggboots"));
                    player.sendMessage(" ");

                }

                if(args[0].equalsIgnoreCase("jugghelm")){

                    player.sendMessage(Utils.chatColor("&8[&a●&8] &7You got " + Armor.JuggHelm().getItemMeta().getDisplayName()));

                    player.getInventory().addItem(Armor.JuggHelm());

                }

                if(args[0].equalsIgnoreCase("juggchestplate")){

                    player.sendMessage(Utils.chatColor("&8[&a●&8] &7You got " + Armor.JuggChestplate().getItemMeta().getDisplayName()));

                    player.getInventory().addItem(Armor.JuggChestplate());

                }

                if(args[0].equalsIgnoreCase("juggpants")){

                    player.sendMessage(Utils.chatColor("&8[&a●&8] &7You got " + Armor.JuggPants().getItemMeta().getDisplayName()));

                    player.getInventory().addItem(Armor.JuggPants());

                }

                if(args[0].equalsIgnoreCase("juggboots")){

                    player.sendMessage(Utils.chatColor("&8[&a●&8] &7You got " + Armor.JuggBoots().getItemMeta().getDisplayName()));

                    player.getInventory().addItem(Armor.JuggBoots());

                }

            }else{

                player.sendMessage(" ");
                player.sendMessage(Utils.chatColor("&c/armor <armorname> &7- &7&oGives you a desired armor!"));
                player.sendMessage(Utils.chatColor("&c/armor list &7- &7&oDisplays a list of available armor!"));
                player.sendMessage(" ");

            }

        }

        return false;
    }
}
