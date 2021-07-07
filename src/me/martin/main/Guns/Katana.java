package me.martin.main.Guns;

import de.tr7zw.nbtapi.NBTItem;
import me.martin.main.Main;
import me.martin.main.Utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Katana implements Listener {

    Main main;

    public Katana(Main main){

        this.main = main;

    }

    @EventHandler
    public void katanaHit(EntityDamageByEntityEvent e){

        double damage = main.getConfig().getDouble("Guns.Katana.Damage");

        if(e.getDamager() instanceof Player){

            Player damager = ((Player) e.getDamager()).getPlayer();

            if(damager.getItemInHand() != null){

            NBTItem gun = new NBTItem(damager.getItemInHand());

            if(damager.getItemInHand().getType().equals(Material.DOUBLE_PLANT) && damager.getItemInHand().getData().getData() == (byte) 4) {
                if (gun.hasKey("durability")) {
                    if (gun.getInteger("durability") != 1) {

                        e.setDamage(damage);

                        gun.setInteger("durability", gun.getInteger("durability") - 1);
                        damager.setItemInHand(gun.getItem());

                        ItemStack gun2 = damager.getItemInHand();
                        ItemMeta gun2Meta = gun2.getItemMeta();

                        gun2Meta.setDisplayName(Utils.chatColor("&4Katana &8◆ «&7" + gun.getInteger("durability") + Utils.chatColor("&8»")));
                        gun2.setItemMeta(gun2Meta);
                        damager.setItemInHand(gun2);

                    } else {

                        damager.setItemInHand(null);

                    }
                }
            }
            }

        }

    }

}
