package me.martin.main.Commands;

import me.martin.main.Main;
import me.martin.main.Utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConfigReload implements CommandExecutor {

    Main main;

    public ConfigReload(Main main){

        this.main = main;

    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender instanceof Player){

            Player player = (Player) commandSender;

            if(args.length > 0) {

                if(args[0].equalsIgnoreCase("reload")) {

                    main.reloadConfig();

                    player.sendMessage(Utils.chatColor("&8[&a●&8] &7Config has been reloaded!"));

                }
            }else{

                player.sendMessage(" ");
                player.sendMessage(Utils.chatColor("&c/config reload &7- &7&oReloads configuration file"));
                player.sendMessage(" ");

            }
        }

        return false;
    }
}
