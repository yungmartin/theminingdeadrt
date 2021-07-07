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
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Bandages implements Listener {

    Main main;

    public Bandages(Main main){

        this.main = main;

    }

    public ItemStack bandageWrapped(Player player){

        ItemStack bandageWrapped = new ItemStack(388, bandageAmount.get(player));

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

    public ItemStack bandageUnwrapped(Player player){

        ItemStack bandageUnwrapped = new ItemStack(372, bandageAmount.get(player));

        ItemMeta bandageUnwrappedMeta = bandageUnwrapped.getItemMeta();

        List<String> lore = new ArrayList<>();

        lore.add(Utils.chatColor(" "));
        lore.add(Utils.chatColor("&8Heals: &72.5 Hearts"));
        lore.add(Utils.chatColor("&8Use Time: &71.3 Seconds"));
        lore.add(Utils.chatColor(" "));
        lore.add(Utils.chatColor("&8(&7Medical&8)"));


        bandageUnwrappedMeta.setDisplayName(Utils.chatColor("&dBandage"));
        bandageUnwrappedMeta.setLore(lore);
        bandageUnwrapped.setItemMeta(bandageUnwrappedMeta);

        return bandageUnwrapped;

    }

    HashSet<Player> bandageUseCooldown = new HashSet<>();
    HashMap<Player, Integer> bandageAmount = new HashMap<>();

    @EventHandler
    public void bandageUse(PlayerInteractEvent e){

        Player player = e.getPlayer();

        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)){
            if(player.getInventory().getItemInHand().getTypeId() == 388){
                if(!bandageUseCooldown.contains(player)){

                    new BukkitRunnable(){


                        @Override
                        public void run() {

                            bandageUseCooldown.add(player);

                            bandageAmount.put(player,player.getItemInHand().getAmount());

                            player.setItemInHand(bandageUnwrapped(player));

                            new BukkitRunnable(){
                                double bandageUsingTime = 1.3;
                                int progress;


                                @Override
                                public void run() {

                                    if (bandageUseCooldown.contains(player)) {
                                        if (bandageUsingTime <= 0.1) {

                                            bandageUseCooldown.remove(player);
                                            PacketPlayOutChat healComplete = new PacketPlayOutChat(new ChatComponentText(Utils.chatColor("&aHealing completed")), (byte) 2);
                                            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(healComplete);
                                            if (player.getHealth() < 20.0 && player.getHealth() > 15.0 || player.getHealth() == 20.0) {

                                                player.setHealth(20.0);

                                            } else {

                                                player.setHealth(player.getHealth() + 5.0);

                                            }
                                            player.setItemInHand(bandageWrapped(player));
                                            player.spigot().playEffect(player.getLocation(), Effect.HEART, 1, 1, 0.7f, 0, 0.5f, 0, 6, 8);
                                            if (player.getInventory().getItemInHand() != null && player.getInventory().getItemInHand().getAmount() > 1) {
                                                player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
                                            } else {
                                                player.getInventory().setItemInHand(null);
                                            }

                                            this.cancel();

                                        } else {

                                            bandageUsingTime -= 0.1;
                                            progress++;
                                            PacketPlayOutChat healingMessage = new PacketPlayOutChat(new ChatComponentText("§3Healing " + "§0§l[" + getProgressBar(progress, 13, 20, '❙', ChatColor.GREEN, ChatColor.RED) + "§0§l] §b" + String.format("%.1f", bandageUsingTime) + " seconds"), (byte) 2);
                                            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(healingMessage);

                                        }
                                    } else {
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
                            }.runTaskTimer(main, 0, 2);

                        }
                    }.runTaskLater(main, 2);
                }
            }
        }
    }

    @EventHandler
    public void onItemSwitch(PlayerItemHeldEvent e){

        Player player = e.getPlayer();

        if(bandageUseCooldown.contains(player)){

            PacketPlayOutChat healingMessage = new PacketPlayOutChat(new ChatComponentText("§cHealing cancelled"), (byte) 2);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(healingMessage);
            bandageUseCooldown.remove(player);
            player.setItemInHand(bandageWrapped(player));

        }

    }

}
