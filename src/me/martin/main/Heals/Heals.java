package me.martin.main.Heals;

import me.martin.main.Utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Heals {

    public static ItemStack bandageWrapped(Integer amount){

        ItemStack bandageWrapped = new ItemStack(388, amount);

        ItemMeta bandageWrappedMeta = bandageWrapped.getItemMeta();

        List<String> lore = new ArrayList<>();

        lore.add(Utils.chatColor(" "));
        lore.add(Utils.chatColor("&8Heals: &72.5 Hearts"));
        lore.add(Utils.chatColor("&8Use Time: &71.3 Seconds"));
        lore.add(Utils.chatColor(" "));
        lore.add(Utils.chatColor("&8(&7Medical&8)"));


        bandageWrappedMeta.setDisplayName(Utils.chatColor("&dBandage"));
        bandageWrappedMeta.setLore(lore);
        bandageWrapped.setItemMeta(bandageWrappedMeta);

        return bandageWrapped;

    }

    public static ItemStack Painkillers(Integer amount){

        ItemStack painkillers = new ItemStack(Material.FIREWORK_CHARGE, amount);

        ItemMeta painkillersMeta = painkillers.getItemMeta();

        List<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(Utils.chatColor("&8Gives: &7Regeneration II"));
        lore.add(Utils.chatColor("&8Duration: &720 Seconds"));
        lore.add(Utils.chatColor("&8Use Time: &70.1 Second"));
        lore.add(" ");
        lore.add(Utils.chatColor("&8(&7Medical&8)"));

        painkillersMeta.setDisplayName(Utils.chatColor("&dPainkillers"));
        painkillersMeta.setLore(lore);
        painkillers.setItemMeta(painkillersMeta);

        return painkillers;

    }





}
