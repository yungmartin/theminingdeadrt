package me.martin.main.Armor;

import de.tr7zw.nbtapi.NBTItem;
import me.martin.main.Utils.Utils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Armor {

    public static ItemStack JuggHelm(){

        List<String> lore = new ArrayList<>();

        NBTItem armor = new NBTItem(new ItemStack(Material.DIAMOND_HELMET));
        armor.setInteger("durability", 500);

        ItemMeta armorMeta = armor.getItem().getItemMeta();

        armorMeta.setDisplayName(Utils.chatColor("&2Juggernaut Mask &8◆ «&7" + armor.getInteger("durability") + "&8»"));

        lore.add(" ");
        lore.add(Utils.chatColor("&8Armor Level: &77.0"));
        lore.add(" ");
        lore.add(Utils.chatColor("&8(&2Legend&8)"));

        armorMeta.spigot().setUnbreakable(true);

        armorMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        armorMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        armorMeta.setLore(lore);

        armor.getItem().setItemMeta(armorMeta);

        return armor.getItem();

    }

    public static ItemStack JuggChestplate(){

        List<String> lore = new ArrayList<>();

        NBTItem armor = new NBTItem(new ItemStack(Material.DIAMOND_CHESTPLATE));
        armor.setInteger("durability", 500);

        ItemMeta armorMeta = armor.getItem().getItemMeta();

        armorMeta.setDisplayName(Utils.chatColor("&2Juggernaut Body Armour &8◆ «&7" + armor.getInteger("durability") + "&8»"));

        lore.add(" ");
        lore.add(Utils.chatColor("&8Armor Level: &711.5"));
        lore.add(" ");
        lore.add(Utils.chatColor("&8(&2Legend&8)"));

        armorMeta.spigot().setUnbreakable(true);

        armorMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        armorMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        armorMeta.setLore(lore);

        armor.getItem().setItemMeta(armorMeta);

        return armor.getItem();

    }

    public static ItemStack JuggPants(){

        List<String> lore = new ArrayList<>();

        NBTItem armor = new NBTItem(new ItemStack(Material.DIAMOND_LEGGINGS));
        armor.setInteger("durability", 500);

        ItemMeta armorMeta = armor.getItem().getItemMeta();

        armorMeta.setDisplayName(Utils.chatColor("&2Juggernaut Pants &8◆ «&7" + armor.getInteger("durability") + "&8»"));

        lore.add(" ");
        lore.add(Utils.chatColor("&8Armor Level: &710.5"));
        lore.add(" ");
        lore.add(Utils.chatColor("&8(&2Legend&8)"));

        armorMeta.spigot().setUnbreakable(true);

        armorMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        armorMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        armorMeta.setLore(lore);

        armor.getItem().setItemMeta(armorMeta);

        return armor.getItem();

    }

    public static ItemStack JuggBoots(){

        List<String> lore = new ArrayList<>();

        NBTItem armor = new NBTItem(new ItemStack(Material.DIAMOND_BOOTS));
        armor.setInteger("durability", 500);

        ItemMeta armorMeta = armor.getItem().getItemMeta();

        armorMeta.setDisplayName(Utils.chatColor("&2Juggernaut Boots &8◆ «&7" + armor.getInteger("durability") + "&8»"));

        lore.add(" ");
        lore.add(Utils.chatColor("&8Armor Level: &77.0"));
        lore.add(" ");
        lore.add(Utils.chatColor("&8(&2Legend&8)"));

        armorMeta.spigot().setUnbreakable(true);

        armorMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        armorMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        armorMeta.setLore(lore);

        armor.getItem().setItemMeta(armorMeta);

        return armor.getItem();

    }

}
