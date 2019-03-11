package pl.argania.core.Utils;

import org.bukkit.ChatColor;

public class ChatColorUtils {

        private ChatColorUtils() {
        }

        public static String colored(String message) {
            if (message.isEmpty()) return "";
            else return ChatColor.translateAlternateColorCodes('&', message.replace("<3", "❤").replace(">>", "»").replace("<<", "«"));
        }
    }
