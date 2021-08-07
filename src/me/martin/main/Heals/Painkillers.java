package me.martin.main.Heals;

import com.google.common.base.Strings;
import me.martin.main.Main;
import me.martin.main.Utils.Utils;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Painkillers implements Listener {

    Main main;

    public Painkillers(Main main){

        this.main = main;

    }

    public ItemStack Painkillers(Player player){

        ItemStack painkillers = new ItemStack(Material.FIREWORK_CHARGE, painkillersAmount.get(player));

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

    public ItemStack OpenedPainkillers(Player player){

        ItemStack painkillers = new ItemStack(289, painkillersAmount.get(player));

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

    HashSet<Player> painkillersUseCooldown = new HashSet<>();

    HashMap<Player, Integer> painkillersAmount = new HashMap<>();

    @EventHandler
    public void painkillers(PlayerInteractEvent e){

        Player player = e.getPlayer();

        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)){
            if(player.getItemInHand().getType().equals(Material.FIREWORK_CHARGE)){
                if(!painkillersUseCooldown.contains(player)){

                    new BukkitRunnable(){


                        @Override
                        public void run() {

                            painkillersUseCooldown.add(player);

                            painkillersAmount.put(player, player.getItemInHand().getAmount());

                            player.setItemInHand(OpenedPainkillers(player));

                            new BukkitRunnable(){

                                double painkillerUseTime = 0.5;
                                int progress;

                                @Override
                                public void run() {

                                    if(painkillersUseCooldown.contains(player)){
                                        if(painkillerUseTime <= 0.1){

                                            painkillersUseCooldown.remove(player);

                                            PacketPlayOutChat healComplete = new PacketPlayOutChat(new ChatComponentText(Utils.chatColor("&aHealing completed")), (byte) 2);
                                            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(healComplete);

                                            player.removePotionEffect(PotionEffectType.REGENERATION);
                                            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 500, 1));

                                            player.setItemInHand(Painkillers(player));

                                            player.spigot().playEffect(player.getLocation(), Effect.HEART, 1, 1, 0.7f, 0, 0.5f, 0, 6, 8);

                                            if (player.getInventory().getItemInHand() != null && player.getInventory().getItemInHand().getAmount() > 1) {
                                                player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
                                            } else {
                                                player.getInventory().setItemInHand(null);
                                            }

                                            this.cancel();

                                        }else{

                                            painkillerUseTime -= 0.1;
                                            progress++;

                                            PacketPlayOutChat healingMessage = new PacketPlayOutChat(new ChatComponentText("§3Healing " + "§0§l[" + getProgressBar(progress, 5, 20, '❙', ChatColor.GREEN, ChatColor.RED) +  "§0§l] §b" + String.format("%.1f", painkillerUseTime) + " seconds"), (byte) 2);
                                            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(healingMessage);

                                        }


                                    }else{

                                        this.cancel();

                                    }

                                }

                                public String getProgressBar(int current, int max, int totalBars, char symbol, ChatColor completedColor,
                                                             ChatColor notCompletedColor) {
                                    float percent = (float) current / max;
                                    int progressBars = (int) (totalBars * percent);

                                    return Strings.repeat("" + completedColor + symbol, progressBars)
                                            + Strings.repeat("" + notCompletedColor + symbol, totalBars - progressBars);
                                }

                            }.runTaskTimer(main, 0 ,2);

                        }
                    }.runTaskLater(main, 2);

                }

            }

        }

    }

    @EventHandler
    public void onItemSwitch(PlayerItemHeldEvent e){

        Player player = e.getPlayer();

        if(painkillersUseCooldown.contains(player)){

            new BukkitRunnable(){


                @Override
                public void run() {

                    PacketPlayOutChat healingMessage = new PacketPlayOutChat(new ChatComponentText("§cHealing cancelled"), (byte) 2);
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(healingMessage);

                }
            }.runTaskLater(main, 1);

            painkillersUseCooldown.remove(player);
            player.setItemInHand(Painkillers(player));

        }

    }

}
