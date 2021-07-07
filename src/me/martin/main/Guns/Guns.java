package me.martin.main.Guns;

import de.tr7zw.nbtapi.NBTItem;
import me.martin.main.Utils.Utils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Guns {

    public static ItemStack Barrett(){

        List<String> lore = new ArrayList<>();

        NBTItem barrett = new NBTItem(new ItemStack(Material.STONE_AXE));
        barrett.setInteger("ammo", 10);

        ItemMeta barrettMeta = barrett.getItem().getItemMeta();

        barrettMeta.setDisplayName(Utils.chatColor("&bBarrett .50 Cal"));

        lore.add(" ");
        lore.add("§8Damage: §713.0 (20.0 HS)");
        lore.add("§8Accuracy: §7100%");
        lore.add("§8Fire Rate: §7High");
        lore.add("§8Recoil: §7None");
        lore.add(" ");
        lore.add("§8Ammo: §7" + barrett.getInteger("ammo") + "§8/§7" + barrett.getInteger("ammo") + "⑤");
        lore.add("§8Type: §7Sniper");
        lore.add(" ");
        lore.add("§8(§bTier 4§8)");

        barrettMeta.spigot().setUnbreakable(true);

        barrettMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        barrettMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        barrettMeta.setLore(lore);

        barrett.getItem().setItemMeta(barrettMeta);


        return barrett.getItem();

    }

    public static ItemStack GoldenBarrett(){

        List<String> lore = new ArrayList<>();

        NBTItem gun = new NBTItem(new ItemStack(Material.INK_SACK, (byte) 1, (short) 7));
        gun.setInteger("ammo", 10);

        ItemMeta goldenBarrettMeta = gun.getItem().getItemMeta();

        goldenBarrettMeta.setDisplayName(Utils.chatColor("&2Golden Barrett .50 Cal"));

        lore.add(" ");
        lore.add("§8Damage: §713.5 (20.0 HS)");
        lore.add("§8Accuracy: §7100%");
        lore.add("§8Fire Rate: §7High");
        lore.add("§8Recoil: §7None");
        lore.add(" ");
        lore.add("§8Ammo: §7" + gun.getInteger("ammo") + "§8/§7" + gun.getInteger("ammo") + "⑤");
        lore.add("§8Type: §7Sniper");
        lore.add(" ");
        lore.add("§8(§2Legend§8)");

        goldenBarrettMeta.spigot().setUnbreakable(true);

        goldenBarrettMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        goldenBarrettMeta.setLore(lore);

        gun.getItem().setItemMeta(goldenBarrettMeta);

        return gun.getItem();

    }

    public static ItemStack Deagle(){

        List<String> lore = new ArrayList<>();

        NBTItem gun = new NBTItem(new ItemStack(Material.WOOD_AXE, 1));
        gun.setInteger("ammo", 7);

        ItemMeta deagleMeta = gun.getItem().getItemMeta();

        deagleMeta.setDisplayName(Utils.chatColor("&bDesert Eagle"));

        lore.add(" ");
        lore.add("§8Damage: §79.5 (14.0 HS)");
        lore.add("§8Accuracy: §7100%");
        lore.add("§8Fire Rate: §7High");
        lore.add("§8Recoil: §7Medium");
        lore.add(" ");
        lore.add("§8Ammo: §7" + gun.getInteger("ammo") + "§8/§7" + gun.getInteger("ammo") + "③");
        lore.add("§8Type: §7Pistol");
        lore.add(" ");
        lore.add("§8(§bTier 4§8)");

        deagleMeta.spigot().setUnbreakable(true);

        deagleMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        deagleMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        deagleMeta.setLore(lore);

        gun.getItem().setItemMeta(deagleMeta);

        return gun.getItem();

    }

    public static ItemStack GoldenDeagle(){

        List<String> lore = new ArrayList<>();

        NBTItem gun = new NBTItem(new ItemStack(Material.INK_SACK, (byte) 1, (short) 14));
        gun.setInteger("ammo", 7);

        ItemMeta goldenDeagleMeta = gun.getItem().getItemMeta();

        goldenDeagleMeta.setDisplayName(Utils.chatColor("&2Golden Desert Eagle"));

        lore.add(" ");
        lore.add("§8Damage: §79.5 (14.0 HS)");
        lore.add("§8Accuracy: §7100%");
        lore.add("§8Fire Rate: §7High");
        lore.add("§8Recoil: §7None");
        lore.add(" ");
        lore.add("§8Ammo: §7" + gun.getInteger("ammo") + "§8/§7" + gun.getInteger("ammo") + "③");
        lore.add("§8Type: §7Pistol");
        lore.add(" ");
        lore.add("§8(§2Legend§8)");

        goldenDeagleMeta.spigot().setUnbreakable(true);

        goldenDeagleMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        goldenDeagleMeta.setLore(lore);

        gun.getItem().setItemMeta(goldenDeagleMeta);

        return gun.getItem();

    }

    public static ItemStack Spas(){

        List<String> lore = new ArrayList<>();

        NBTItem gun = new NBTItem(new ItemStack(Material.IRON_AXE));
        gun.setInteger("ammo", 6);

        ItemMeta spasMeta = gun.getItem().getItemMeta();

        spasMeta.setDisplayName(Utils.chatColor("&bSpas 12"));

        lore.add(" ");
        lore.add("§8Damage: §70.85/Bullet (1.2 HS)");
        lore.add("§8Bullets Per Shot: §714");
        lore.add("§8Accuracy: §7100%");
        lore.add("§8Fire Rate: §7High");
        lore.add("§8Recoil: §7Medium");
        lore.add(" ");
        lore.add("§8Ammo: §7" + gun.getInteger("ammo") + "§8/§7" + gun.getInteger("ammo") + "⑧");
        lore.add("§8Type: §7Shotgun");
        lore.add(" ");
        lore.add("§8(§bTier 4§8)");

        spasMeta.spigot().setUnbreakable(true);

        spasMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        spasMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        spasMeta.setLore(lore);

        gun.getItem().setItemMeta(spasMeta);

        return gun.getItem();

    }

    public static ItemStack Katana(){

        List<String> lore = new ArrayList<>();

        NBTItem gun = new NBTItem(new ItemStack(Material.DOUBLE_PLANT, (short) 1, (byte) 4));
        gun.setInteger("durability", 650);

        ItemMeta katanaMeta = gun.getItem().getItemMeta();

        katanaMeta.setDisplayName(Utils.chatColor("&4Katana &8◆ «&7" + gun.getInteger("durability") + Utils.chatColor("&8»")));

        lore.add(" ");
        lore.add(Utils.chatColor("&8Damage: &710.0"));
        lore.add(" ");
        lore.add(Utils.chatColor("&8(&7Melee&8)"));

        katanaMeta.spigot().setUnbreakable(true);

        katanaMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        katanaMeta.setLore(lore);

        gun.getItem().setItemMeta(katanaMeta);

        return gun.getItem();

    }

    public static ItemStack PinkPrecision(){

        List<String> lore = new ArrayList<>();

        NBTItem gun = new NBTItem(new ItemStack(Material.STAINED_CLAY, (short) 1, (byte) 10));
        gun.setInteger("ammo", 7);

        ItemMeta pinkPrecisionMeta = gun.getItem().getItemMeta();

        pinkPrecisionMeta.setDisplayName(Utils.chatColor("&2Pink Precision"));

        lore.add(" ");
        lore.add(Utils.chatColor("&8Damage: &713.5 (20.0 HS)"));
        lore.add(Utils.chatColor("&8Accuracy: &7100%"));
        lore.add(Utils.chatColor("&8Fire Rate: &7High"));
        lore.add(Utils.chatColor("&8Recoil: &7None"));
        lore.add(" ");
        lore.add("§8Ammo: §7" + gun.getInteger("ammo") + "§8/§7" + gun.getInteger("ammo") + "⑤");
        lore.add(Utils.chatColor("&8Type: &7Sniper"));
        lore.add(" ");
        lore.add(Utils.chatColor("&8(&2Legend&8)"));

        pinkPrecisionMeta.spigot().setUnbreakable(true);

        pinkPrecisionMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        pinkPrecisionMeta.setLore(lore);

        gun.getItem().setItemMeta(pinkPrecisionMeta);

        return gun.getItem();

    }

}
