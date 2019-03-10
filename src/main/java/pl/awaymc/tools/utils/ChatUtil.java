package pl.awaymc.tools.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import java.util.*;
import java.util.regex.Pattern;

public class ChatUtil
{
    public static void giveItems(final Player p, final ItemStack... items) {
        final Inventory i = (Inventory)p.getInventory();
        final HashMap<Integer, ItemStack> notStored = (HashMap<Integer, ItemStack>)i.addItem(items);
        for (final Map.Entry<Integer, ItemStack> e : notStored.entrySet()) {
            p.getWorld().dropItemNaturally(p.getLocation(), (ItemStack)e.getValue());
        }
    }
    public static String fixColor(String text) {
        return ChatColor.translateAlternateColorCodes('&', text.replace(">>", "»").replace("<<", "«"));
    }
    public static void sendMessage(Player p, String text) {
        p.sendMessage(fixColor(text));
    }
    public static boolean isInteger(final String string) {
        return Pattern.matches("-?[0-9]+", string.subSequence(0, string.length()));
    }
}
