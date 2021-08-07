package me.martin.main.Armor;

import de.tr7zw.nbtapi.NBTItem;
import me.martin.main.Utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ArmorEvent implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {

        if (e.getEntity() instanceof Player) {

            Player player = ((Player) e.getEntity()).getPlayer();

            if (!(player.getInventory().getArmorContents() == null)) {

                NBTItem armorHelm = new NBTItem(player.getInventory().getHelmet());
                NBTItem armorChestplate = new NBTItem(player.getInventory().getChestplate());
                NBTItem armorPants = new NBTItem(player.getInventory().getLeggings());
                NBTItem armorBoots = new NBTItem(player.getInventory().getBoots());


                if (player.getInventory().getHelmet().getType() == Material.DIAMOND_HELMET
                        && armorHelm.hasKey("durability")) {
                    if (armorHelm.getInteger("durability") != 1) {

                        armorHelm.setInteger("durability", armorHelm.getInteger("durability") - 1);
                        player.getInventory().setHelmet(armorHelm.getItem());

                        ItemStack armorHelm2 = new ItemStack(player.getInventory().getHelmet());
                        ItemMeta armorHelm2Meta = armorHelm2.getItemMeta();

                        armorHelm2Meta.setDisplayName(Utils.chatColor("&2Juggernaut Mask &8◆ «&7" + armorHelm.getInteger("durability") + "&8»"));
                        armorHelm2.setItemMeta(armorHelm2Meta);

                        player.getInventory().setHelmet(armorHelm2);

                    } else {

                        player.getInventory().setHelmet(null);

                    }

                    if (player.getInventory().getChestplate().getType() == Material.DIAMOND_CHESTPLATE
                            && armorChestplate.hasKey("durability")) {
                        if (armorChestplate.getInteger("durability") != 1) {

                            armorChestplate.setInteger("durability", armorChestplate.getInteger("durability") - 1);
                            player.getInventory().setChestplate(armorChestplate.getItem());

                            ItemStack armorHelm2 = new ItemStack(player.getInventory().getChestplate());
                            ItemMeta armorHelm2Meta = armorHelm2.getItemMeta();

                            armorHelm2Meta.setDisplayName(Utils.chatColor("&2Juggernaut Body Armour &8◆ «&7" + armorChestplate.getInteger("durability") + "&8»"));
                            armorHelm2.setItemMeta(armorHelm2Meta);

                            player.getInventory().setChestplate(armorHelm2);

                        } else {

                            player.getInventory().setChestplate(null);

                        }

                        if (player.getInventory().getLeggings().getType() == Material.DIAMOND_LEGGINGS
                                && armorPants.hasKey("durability")) {
                            if (armorPants.getInteger("durability") != 1) {

                                armorPants.setInteger("durability", armorPants.getInteger("durability") - 1);
                                player.getInventory().setLeggings(armorPants.getItem());

                                ItemStack armorHelm2 = new ItemStack(player.getInventory().getLeggings());
                                ItemMeta armorHelm2Meta = armorHelm2.getItemMeta();

                                armorHelm2Meta.setDisplayName(Utils.chatColor("&2Juggernaut Pants &8◆ «&7" + armorPants.getInteger("durability") + "&8»"));
                                armorHelm2.setItemMeta(armorHelm2Meta);

                                player.getInventory().setLeggings(armorHelm2);

                            } else {

                                player.getInventory().setLeggings(null);

                            }

                            if (player.getInventory().getBoots().getType() == Material.DIAMOND_BOOTS
                                    && armorBoots.hasKey("durability")) {
                                if (armorBoots.getInteger("durability") != 1) {

                                    armorBoots.setInteger("durability", armorBoots.getInteger("durability") - 1);
                                    player.getInventory().setBoots(armorBoots.getItem());

                                    ItemStack armorHelm2 = new ItemStack(player.getInventory().getBoots());
                                    ItemMeta armorHelm2Meta = armorHelm2.getItemMeta();

                                    armorHelm2Meta.setDisplayName(Utils.chatColor("&2Juggernaut Boots &8◆ «&7" + armorBoots.getInteger("durability") + "&8»"));
                                    armorHelm2.setItemMeta(armorHelm2Meta);

                                    player.getInventory().setBoots(armorHelm2);

                                } else {

                                    player.getInventory().setBoots(null);

                                }


                            }

                        }

                    }

                }
            }
        }
    }

}
