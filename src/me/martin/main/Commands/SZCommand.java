package me.martin.main.Commands;

import me.martin.main.Main;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

public class SZCommand implements CommandExecutor {

    Main main;

    public SZCommand(Main main){

        this.main = main;

    }

    HashSet<Player> enteringSafezone = new HashSet<>();


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;

            Inventory szGui = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Safezones");

            ItemStack woodenSword = new ItemStack(Material.WOOD_SWORD);
            ItemStack szInfo = new ItemStack(Material.PUMPKIN_SEEDS);
            ItemMeta szInfoMeta = szInfo.getItemMeta();
            ArrayList<String> lore = new ArrayList<>();
            lore.add("");
            lore.add("§7Safezones are safe areas");
            lore.add("§7on the map with §eshops§7,§efarms§7,");
            lore.add("§7and §eother useful items§7.");
            szInfoMeta.setDisplayName("§3§lSafezone");
            szInfoMeta.setLore(lore);
            szInfo.setItemMeta(szInfoMeta);
            ItemMeta woodenSwordmeta = woodenSword.getItemMeta();
            woodenSwordmeta.setDisplayName(" ");
            woodenSword.setItemMeta(woodenSwordmeta);
            ItemStack berxButton = new ItemStack(Material.CLAY);
            ItemMeta berxMeta = berxButton.getItemMeta();
            ArrayList<String> lore1 = new ArrayList<>();
            berxMeta.setDisplayName("§3Berxley Safehaven");
            lore1.add(" ");
            lore1.add("§eClick to warp");
            berxMeta.setLore(lore1);
            berxButton.setItemMeta(berxMeta);
            ItemStack schoolButton = new ItemStack(Material.BRICK);
            ItemMeta schoolMeta = schoolButton.getItemMeta();
            ArrayList<String> lore2 = new ArrayList<>();
            schoolMeta.setDisplayName("§3School Safezone");
            lore2.add(" ");
            lore2.add("§eClick to warp");
            schoolMeta.setLore(lore2);
            schoolButton.setItemMeta(schoolMeta);

            for (int slot = 0; slot < szGui.getSize(); slot++) {
                szGui.setItem(slot, woodenSword);
                szGui.setItem(10, berxButton);
                szGui.setItem(11, schoolButton);
                szGui.setItem(8, szInfo);
            }
            if (args.length > 0) {
                UUID id = p.getUniqueId();
                float pitch = (float) -0.6;
                float yaw = (float) 179.7;

                Location spawn = new Location(p.getWorld(), 1678.564, 74, 574.605, yaw, pitch);

                PacketPlayOutTitle spawnCountdownTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
                        IChatBaseComponent.ChatSerializer.a("{\"text\":\"§bTeleporting\"}"), 1, 10, 5);

                PacketPlayOutTitle spawnCountdownSubtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
                        IChatBaseComponent.ChatSerializer.a("{\"text\":\"§fDon't move\"}"), 1, 160, 5);
                PacketPlayOutTitle spawnCountdownSubtitleGray = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
                        IChatBaseComponent.ChatSerializer.a("{\"text\":\"§7Don't move\"}"), 1, 10, 5);

                PacketPlayOutTitle spawnSuccessSubTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
                        IChatBaseComponent.ChatSerializer.a("{\"text\":\"§bTeleporting\"}"), 1, 1, 1);

                PacketPlayOutTitle spawnSuccessTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
                        IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + " " + "\"}"), 1, 10, 5);

                PacketPlayOutTitle spawnFailedSubTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
                        IChatBaseComponent.ChatSerializer.a("{\"text\":\"§7You moved\"}"), 1, 10, 5);

                PacketPlayOutTitle spawnFailedTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
                        IChatBaseComponent.ChatSerializer.a("{\"text\":\"§CTeleportation Failed\"}"), 1, 10, 5);

                enteringSafezone.add(p);

                new BukkitRunnable() {
                    int timer = 8;

                    @Override
                    public void run() {
                        if (enteringSafezone.contains(p)) {
                            if (timer == 0) {
                                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(spawnSuccessSubTitle);
                                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(spawnSuccessTitle);
                                p.teleport(spawn);
                                p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 2 * 20, 5));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 2 * 20, 5));
                                p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + ChatColor.BOLD + "●" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY + "Teleporting...");
                                this.cancel();
                                enteringSafezone.remove(p);
                            } else {
                                PacketPlayOutTitle countdown = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
                                        IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + ChatColor.GREEN + "" + ChatColor.BOLD + timer-- + "\"}"), 1, 10, 5);
                                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(countdown);
                                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(spawnCountdownSubtitleGray);
                            }
                        } else {
                            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(spawnFailedSubTitle);
                            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(spawnFailedTitle);
                            this.cancel();
                        }
                    }
                }.runTaskTimer(main, 8, 20);

            } else {
                p.openInventory(szGui);
            }
        }
        return false;
    }
}
