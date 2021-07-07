package me.martin.main;

import me.martin.main.Armor.ArmorEvent;
import me.martin.main.ChatUtils.ChatFormat;
import me.martin.main.ChatUtils.Join;
import me.martin.main.Commands.*;
import me.martin.main.Deaths.Deaths;
import me.martin.main.Guns.*;
import me.martin.main.Heals.Bandages;
import me.martin.main.Heals.Painkillers;
import me.martin.main.Heals.Syringes;
import me.martin.main.Scoreboards.ScoreboardUpdateEvents.SBUpdate;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable(){

        registerEvents();

        registerCommands();

        config();

    }

    @Override
    public void onDisable(){



    }

    public void registerEvents(){

        PluginManager pluginManager = Bukkit.getPluginManager();

        //Guns

        pluginManager.registerEvents(new Barrett(this), this);
        pluginManager.registerEvents(new DesertEagle(this), this);
        pluginManager.registerEvents(new GoldenDeagle(this), this);
        pluginManager.registerEvents(new GoldenBarrett(this), this);
        pluginManager.registerEvents(new PinkPrecision(this), this);
        pluginManager.registerEvents(new Katana(this), this);
        pluginManager.registerEvents(new Spas(this), this);

        //Heals

        pluginManager.registerEvents(new Bandages(this), this);
        pluginManager.registerEvents(new Syringes(this), this);
        pluginManager.registerEvents(new Painkillers(this), this);

        //Other

        pluginManager.registerEvents(new SBUpdate(this), this);
        pluginManager.registerEvents(new GunUtils(this), this);
        pluginManager.registerEvents(new Deaths(this), this);
        pluginManager.registerEvents(new ArmorEvent(), this);
        pluginManager.registerEvents(new SzGUI(), this);
        pluginManager.registerEvents(new ChatFormat(), this);
        pluginManager.registerEvents(new Join(), this);

    }

    public void registerCommands(){

        getCommand("heals").setExecutor(new GiveHeals(this));
        getCommand("gun").setExecutor(new GiveGuns(this));
        getCommand("config").setExecutor(new ConfigReload(this));
        getCommand("armor").setExecutor(new GiveArmor(this));
        getCommand("sz").setExecutor(new SZCommand(this));

    }

    public void config(){

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

    }

}
