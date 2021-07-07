package me.martin.main.Utils;

import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Utils {

    public static String chatColor(String string){

        return ChatColor.translateAlternateColorCodes('&', string);

    }

    public static void actionBarMessage(Player player, String message){

        PacketPlayOutChat actionBarMessage = new PacketPlayOutChat(new ChatComponentText(Utils.chatColor(message)), (byte) 2);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(actionBarMessage);

    }

}
