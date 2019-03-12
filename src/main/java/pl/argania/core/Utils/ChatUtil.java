package pl.argania.core.Utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatUtil {

    public static String fixColor(String text) {
        return ChatColor.translateAlternateColorCodes('&', text.replace(">>", "»").replace("<<", "«"));
    }
    public static void sendMessage(Player p, String text) {
        p.sendMessage(fixColor(text));
    }
}