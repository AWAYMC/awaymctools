package net.argania.core.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ChatUtil {

    public static String fixColor(String text) {
        return ChatColor.translateAlternateColorCodes('&', text.replace(">>", "�").replace("<<", "�"));
    }
    public static void sendMessage(Player p, String text) {
        p.sendMessage(fixColor(text));
    }

    @SuppressWarnings("deprecation")
    public static UUID nickToUUID(final String name) {
        if (Bukkit.getPlayer(name) != null) {
            return Bukkit.getPlayer(name).getUniqueId();
        }
        if (Bukkit.getOfflinePlayer(name) == null) {
            return null;
        }
        final OfflinePlayer op = Bukkit.getOfflinePlayer(name);
        if (op.hasPlayedBefore()) {
            return op.getUniqueId();
        }
        return null;
    }
}
