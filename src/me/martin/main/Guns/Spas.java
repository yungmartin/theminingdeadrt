package me.martin.main.Guns;

import de.tr7zw.nbtapi.NBTItem;
import me.martin.main.Guns.RayTrace.BoundingBox;
import me.martin.main.Guns.RayTrace.RayTrace;
import me.martin.main.Main;
import me.martin.main.Utils.Utils;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;
import java.util.stream.Collectors;

public class Spas implements Listener {

    Main main;

    public Spas(Main main){

        this.main = main;

    }

    private Set<PacketPlayOutPosition.EnumPlayerTeleportFlags> teleportFlags = new HashSet<>(Arrays.asList(PacketPlayOutPosition.EnumPlayerTeleportFlags.X, PacketPlayOutPosition.EnumPlayerTeleportFlags.Y, PacketPlayOutPosition.EnumPlayerTeleportFlags.Z, PacketPlayOutPosition.EnumPlayerTeleportFlags.X_ROT, PacketPlayOutPosition.EnumPlayerTeleportFlags.Y_ROT));


    HashSet<Player> shootCooldown = new HashSet<>();
    HashSet<Player> reload = new HashSet<>();

    HashSet<Player> comboDelay = new HashSet<>();

    HashMap<Player, Integer> rayTraceLength = new HashMap<>();

    @EventHandler
    public void shoot(PlayerInteractEvent e) {

        Player shooter = e.getPlayer();

        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (shooter.getInventory().getItemInHand().getType().equals(Material.IRON_AXE)) {

                ItemStack spas = e.getItem();
                NBTItem gun = new NBTItem(spas);

                int currentAmmo;

                currentAmmo = gun.getInteger("ammo");

                if (!comboDelay.contains(shooter)) {
                    if (gun.hasKey("ammo")) {
                        if (gun.getInteger("ammo") > 0) {
                            if (!shootCooldown.contains(shooter)) {
                                if (!reload.contains(shooter)) {

                                    /*Projectile bullet = shooter.launchProjectile(Egg.class);
                                    bullet.setShooter(shooter);
                                    bullet.setVelocity(shooter.getEyeLocation().getDirection().multiply(50.0));*/

                                    //Passtrough materials logic
                                    Vector Calcdirection = shooter.getEyeLocation().getDirection();

                                    int shootDistance = 20;

                                    Location startLoc = shooter.getEyeLocation();

                                    for (int i = 0; i < shootDistance; i++) {

                                        Vector blockVec = Calcdirection.clone().multiply(i);

                                        Location blockLoc = startLoc.clone().add(blockVec);

                                        if (blockLoc.getBlock().getType() != Material.AIR
                                                && blockLoc.getBlock().getType() != Material.IRON_FENCE
                                                && blockLoc.getBlock().getType() != Material.LONG_GRASS
                                                && blockLoc.getBlock().getType() != Material.WEB
                                                && blockLoc.getBlock().getType() != Material.LADDER) {

                                            rayTraceLength.put(shooter, i);
                                            break;
                                        }

                                    }

                                    //RayTracing
                                    RayTrace rayTrace = new RayTrace(shooter.getEyeLocation().toVector(), shooter.getEyeLocation().getDirection());

                                    ArrayList<Vector> positions = rayTrace.traverse(rayTraceLength.get(shooter), 0.1);

                                    for (int i = 0; i < positions.size(); i++) {

                                        Location position = positions.get(i).toLocation(shooter.getWorld());

                                        List<org.bukkit.entity.Entity> entities = shooter.getWorld().getNearbyEntities(position, 0.1, 0.1, 0.1).stream().filter(entity -> (entity instanceof Player)).collect(Collectors.toList());

                                        for (Entity entity : entities) {
                                            if (entity instanceof Player) {
                                                Player victim = ((Player) entity).getPlayer();

                                                double victimY = victim.getLocation().getY();

                                                double rayTraceY = position.getY();

                                                if (rayTrace.intersects(new BoundingBox(victim), rayTraceLength.get(shooter), 0.1)) {

                                                    if (victim != shooter) {
                                                        if (rayTraceY - victimY > 1.35D) {
                                                            victim.damage(main.getConfig().getDouble("Guns.Spas.DamageHS"), shooter);
                                                            shooter.playSound(shooter.getLocation(), Sound.NOTE_BASS, 1, 1);

                                                            victim.setMaximumNoDamageTicks(10);
                                                            victim.setNoDamageTicks(Integer.MAX_VALUE);

                                                            victim.setVelocity(shooter.getLocation().getDirection().setY(0.9).normalize().multiply(0.3));

                                                            new BukkitRunnable(){

                                                                @Override
                                                                public void run() {

                                                                    victim.setMaximumNoDamageTicks(20);
                                                                    victim.setNoDamageTicks(Integer.MIN_VALUE);

                                                                }
                                                            }.runTaskLater(main, 1);

                                                        } else {
                                                            victim.damage(main.getConfig().getDouble("Guns.Spas.Damage"), shooter);
                                                            shooter.playSound(shooter.getLocation(), Sound.NOTE_BASS_DRUM, 1, 1);

                                                            victim.setMaximumNoDamageTicks(10);
                                                            victim.setNoDamageTicks(Integer.MAX_VALUE);

                                                            victim.setVelocity(shooter.getLocation().getDirection().setY(0.9).normalize().multiply(0.3));

                                                            new BukkitRunnable(){

                                                                @Override
                                                                public void run() {

                                                                    victim.setMaximumNoDamageTicks(20);
                                                                    victim.setNoDamageTicks(Integer.MIN_VALUE);

                                                                }
                                                            }.runTaskLater(main, 1);

                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                                    shooter.getWorld().playSound(shooter.getLocation(), Sound.IRONGOLEM_THROW, 1, 1);

                                    PacketPlayOutPosition packet = new PacketPlayOutPosition(0.0, 0.0, 0.0, +1.7f,- 7.5f, this.teleportFlags);
                                    ((CraftPlayer) shooter).getHandle().playerConnection.sendPacket(packet);

                                    for (double i = 2; i < 15; i++) {

                                        Location bulletLocation = shooter.getEyeLocation();
                                        Vector direction = bulletLocation.getDirection();
                                        bulletLocation.add(direction.multiply(i));

                                        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {

                                            PacketPlayOutWorldParticles smoke = new PacketPlayOutWorldParticles(EnumParticle.SMOKE_NORMAL, false, (float) bulletLocation.getX(), (float) bulletLocation.getY(), (float) bulletLocation.getZ(), 0, 0, 0, 0, 40, 0, 0, 0);
                                            ((CraftPlayer) onlinePlayers).getHandle().playerConnection.sendPacket(smoke);

                                        }
                                    }

                                    for(int i = 1; i < 2; i++){

                                        Location bulletLocation = shooter.getEyeLocation();
                                        Vector direction = bulletLocation.getDirection();
                                        bulletLocation.add(direction.multiply(i));

                                        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {

                                            PacketPlayOutWorldParticles smoke = new PacketPlayOutWorldParticles(EnumParticle.FIREWORKS_SPARK, false, (float) bulletLocation.getX(), (float) bulletLocation.getY(), (float) bulletLocation.getZ(), 0, 0, 0, 0, 2, 0, 0, 0);
                                            ((CraftPlayer) onlinePlayers).getHandle().playerConnection.sendPacket(smoke);
                                            PacketPlayOutWorldParticles smoke2 = new PacketPlayOutWorldParticles(EnumParticle.VILLAGER_ANGRY, false, (float) bulletLocation.getX(), (float) bulletLocation.getY(), (float) bulletLocation.getZ(), 0, 0, 0, 0, 2, 0, 0, 0);
                                            ((CraftPlayer) onlinePlayers).getHandle().playerConnection.sendPacket(smoke2);

                                        }

                                    }

                                    currentAmmo--;
                                    gun.setInteger("ammo", currentAmmo);

                                    shooter.setItemInHand(gun.getItem());
                                    shooter.updateInventory();

                                    Utils.actionBarMessage(shooter, ("§e" + currentAmmo + "⑧"));

                                    shootCooldown.add(shooter);

                                    new BukkitRunnable() {


                                        @Override
                                        public void run() {

                                            shootCooldown.remove(shooter);

                                        }
                                    }.runTaskLater(main, 16);


                                } else {

                                    reload.remove(shooter);

                                }


                            }

                        } else if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR) && shooter.getItemInHand().getType().equals(Material.IRON_AXE) && currentAmmo == 0 && !(shooter.getInventory().contains(Material.GHAST_TEAR))) {

                            PacketPlayOutChat noammo = new PacketPlayOutChat(new ChatComponentText("§e" + "0" + "⑧"), (byte) 2);
                            ((CraftPlayer) shooter).getHandle().playerConnection.sendPacket(noammo);

                            shooter.playSound(shooter.getLocation(), Sound.ENDERDRAGON_DEATH, 1, 1);
                        } else if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR) && shooter.getItemInHand().getType().equals(Material.IRON_AXE) && currentAmmo == 0 && !reload.contains(shooter)) {

                            reload.add(shooter);

                            if (reload.contains(shooter)) {


                            }

                            new BukkitRunnable() {

                                double reloadTime = 6.0;

                                @Override
                                public void run() {

                                    if (gun.getInteger("ammo") != 6) {
                                        if (reload.contains(shooter) && shooter.getItemInHand().getType().equals(Material.IRON_AXE)) {

                                                gun.setInteger("ammo", gun.getInteger("ammo") + 1);
                                                shooter.setItemInHand(gun.getItem());

                                            shooter.playSound(shooter.getLocation(), Sound.HORSE_ZOMBIE_DEATH, 1, 1);

                                                PacketPlayOutChat reloading = new PacketPlayOutChat(new ChatComponentText("§7§oReloading... " + String.format("%.1f", reloadTime) + "§7§os §7(" + gun.getInteger("ammo") + "§7/6)"), (byte) 2);
                                                ((CraftPlayer) shooter).getHandle().playerConnection.sendPacket(reloading);

                                                reloadTime--;
                                        } else {

                                            this.cancel();

                                        }
                                    }else{

                                        if(shooter.getItemInHand().getType().equals(Material.STONE_AXE) && reload.contains(shooter)) {

                                            shooter.setItemInHand(gun.getItem());

                                            new BukkitRunnable(){


                                                @Override
                                                public void run() {

                                                    PacketPlayOutChat actionBarAmmo = new PacketPlayOutChat(new ChatComponentText("§e" + gun.getInteger("ammo") + "⑧"), (byte) 2);
                                                    ((CraftPlayer) shooter).getHandle().playerConnection.sendPacket(actionBarAmmo);

                                                }
                                            }.runTaskLater(main, 1);

                                            reload.remove(shooter);

                                            this.cancel();
                                        }
                                    }
                                }
                                }.

                                runTaskTimer(main, 0,20);
                            }
                        }
                }
            }
        }

    @EventHandler
    public void knockback(EntityDamageByEntityEvent e){

        if(e.getDamager() instanceof Egg){

            Egg knockback = (Egg) e.getDamager();

            if(knockback.getShooter() instanceof Player){

                if(e.getEntity() instanceof Player){

                    Player victim = (Player) e.getEntity();

                    e.setDamage(0);

                    new BukkitRunnable(){


                        @Override
                        public void run() {

                            victim.setVelocity(victim.getVelocity().multiply(0.85));

                        }
                    }.runTaskLater(main, 1);

                }

            }

        }

    }


    @EventHandler
    public void reload(PlayerDropItemEvent e){

        Player player = e.getPlayer();

        if(reload.contains(player)){

            e.setCancelled(true);

        }

        if(e.getItemDrop().getItemStack().getType().equals(Material.IRON_AXE)){

            NBTItem gun = new NBTItem(e.getItemDrop().getItemStack());

            if(gun.hasKey("ammo")){
                if(gun.getInteger("ammo") < 6){
                    if(!reload.contains(player)){

                        reload.add(player);

                        e.setCancelled(true);

                        new BukkitRunnable() {

                            double reloadTime = 6.0;

                            @Override
                            public void run() {

                                if (gun.getInteger("ammo") < 6) {
                                    if (reload.contains(player) && player.getItemInHand().getType().equals(Material.IRON_AXE)) {

                                        gun.setInteger("ammo", gun.getInteger("ammo") + 1);
                                        player.setItemInHand(gun.getItem());

                                        player.playSound(player.getLocation(), Sound.HORSE_ZOMBIE_DEATH, 1, 1);

                                        PacketPlayOutChat reloading = new PacketPlayOutChat(new ChatComponentText("§7§oReloading... " + String.format("%.1f", reloadTime) + "§7§os §7(" + gun.getInteger("ammo") + "§7/6)"), (byte) 2);
                                        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(reloading);

                                        reloadTime--;
                                    } else {

                                        this.cancel();

                                    }
                                }else{

                                    if(player.getItemInHand().getType().equals(Material.STONE_AXE) && reload.contains(player)) {

                                        player.setItemInHand(gun.getItem());

                                        PacketPlayOutChat actionBarAmmo = new PacketPlayOutChat(new ChatComponentText("§e" + gun.getInteger("ammo") + "⑧"), (byte) 2);
                                        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(actionBarAmmo);

                                        reload.remove(player);

                                        this.cancel();
                                    }
                                }
                            }
                        }.

                                runTaskTimer(main, 0,20);

                    }

                }else{

                    e.setCancelled(true);

                }
            }

        }

    }

    @EventHandler
    public void displayGunAmmoOnEquip(PlayerItemHeldEvent e){

        Player player = e.getPlayer();

        int comboDelayTime = main.getConfig().getInt("Guns.Spas.ComboDelay");

        reload.remove(player);

        if(player.getInventory().getItem(e.getNewSlot()) != null) {

            NBTItem gun = new NBTItem(player.getInventory().getItem(e.getNewSlot()));

            if (player.getInventory().getItem(e.getNewSlot()).getType().equals(Material.IRON_AXE)) {
                if (gun.hasKey("ammo")) {

                    comboDelay.add(player);

                    PacketPlayOutChat actionBarAmmo1 = new PacketPlayOutChat(new ChatComponentText("§e" + gun.getInteger("ammo") + "⑧"), (byte) 2);
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(actionBarAmmo1);
                    player.playSound(player.getLocation(), Sound.HORSE_ARMOR, 1.0F, 1.0F);

                    new BukkitRunnable() {


                        @Override
                        public void run() {

                            comboDelay.remove(player);

                        }
                    }.runTaskLater(main, comboDelayTime);

                }
            }
        }
    }

}
