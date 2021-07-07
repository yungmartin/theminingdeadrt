package me.martin.main.Heals;

import me.martin.main.Main;
import me.martin.main.Utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Syringes implements Listener {

    Main main;

    public Syringes(Main main){

        this.main = main;

    }

    public static ItemStack syringe(Integer amount){

        ItemStack syringe = new ItemStack(33, amount);

        ItemMeta syringeMeta = syringe.getItemMeta();

        List<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(Utils.chatColor("&8Use Time: &71 Second"));
        lore.add(Utils.chatColor("&8Duration: &71 Minute"));
        lore.add(" ");
        lore.add(Utils.chatColor("&7Provides quick energy boost."));
        lore.add(" ");
        lore.add(Utils.chatColor("&8(&7Medical&8)"));

        syringeMeta.setDisplayName(Utils.chatColor("&dSyringe"));
        syringeMeta.setLore(lore);
        syringe.setItemMeta(syringeMeta);

        return syringe;

    }

    HashSet<Player> syringeUseCooldown = new HashSet<>();

    @EventHandler
    public void syringeUse(PlayerInteractEvent e){

        Player player = e.getPlayer();

        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(player.getInventory().getItemInHand().getTypeId() == 33){
                if(!syringeUseCooldown.contains(player)){

                    if(player.getInventory().getItemInHand() != null && player.getInventory().getItemInHand().getAmount() >= 1){

                        player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);

                    }else{

                        player.getInventory().setItemInHand(null);

                    }

                    player.removePotionEffect(PotionEffectType.SPEED);
                    player.removePotionEffect(PotionEffectType.ABSORPTION);
                    player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200, 0));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 1200, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1200, 0));
                    player.sendMessage("§8[§a●§8] §7You used a §4Syringe§7.");

                    syringeUseCooldown.add(player);

                    new BukkitRunnable(){


                        @Override
                        public void run() {

                            syringeUseCooldown.remove(player);

                        }
                    }.runTaskLater(main, 60*20);

                }

            }
        }

    }

    @EventHandler
    public void syringeDeath(PlayerDeathEvent e){

        Player player = e.getEntity().getPlayer();

        if(syringeUseCooldown.contains(player)){

            syringeUseCooldown.remove(player);

        }

    }

    @EventHandler
    public void syringeQuit(PlayerQuitEvent e){

        Player player = e.getPlayer();

        if(syringeUseCooldown.contains(player)){

            syringeUseCooldown.remove(player);

        }

    }

}
