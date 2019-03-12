package pl.argania.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.argania.core.Utils.ChatUtil;

public class CommandItemShop implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(!sender.hasPermission("argania.core.itemshop")) {
            sender.sendMessage(ChatUtil.fixColor("&4Blad: &cNie masz uprawien (argania.core.itemshop)"));
            return false;
        }
        if(args.length < 1) {
            sender.sendMessage(ChatUtil.fixColor("&4Blad: &cPoprawne uzycie: /itemshop <nick> <vip/unban/key>"));
            return false;
        }
        if(args[1].equalsIgnoreCase("vip")){
            Bukkit.broadcastMessage(ChatUtil.fixColor("&8----------------------------------"));
            Bukkit.broadcastMessage(ChatUtil.fixColor("&7Gracz &c" + args[0] + " &7zakupil range&8: &cVIP"));
            Bukkit.broadcastMessage(ChatUtil.fixColor("&cDziekujemy za wsparcie &4&l<3 "));
            Bukkit.broadcastMessage(ChatUtil.fixColor("&fNasz ItemShop&8: &f&lWWW.ARGANIA.PL/SKLEP"));
            Bukkit.broadcastMessage(ChatUtil.fixColor("&8----------------------------------"));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rank " + args[0] + " vip 30d");
            return true;
        }
        if(args[1].equalsIgnoreCase("unban")) {
            Bukkit.broadcastMessage(ChatUtil.fixColor("&8----------------------------------"));
            Bukkit.broadcastMessage(ChatUtil.fixColor("&7Gracz &c" + args[0] + " &7zakupil usluge&8: &cUNBAN"));
            Bukkit.broadcastMessage(ChatUtil.fixColor("&cDziekujemy za wsparcie &4&l<3 "));
            Bukkit.broadcastMessage(ChatUtil.fixColor("&fNasz ItemShop&8: &f&lWWW.ARGANIA.PL/SKLEP"));
            Bukkit.broadcastMessage(ChatUtil.fixColor("&8----------------------------------"));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "unban" + args[0]);
            return true;
        }
        if(args[1].equalsIgnoreCase("key")) {
            Bukkit.broadcastMessage(ChatUtil.fixColor("&8----------------------------------"));
            Bukkit.broadcastMessage(ChatUtil.fixColor("&7Gracz &c" + args[0] + " &7zakupil usluge&8: &cKLUCZE"));
            Bukkit.broadcastMessage(ChatUtil.fixColor("&cDziekujemy za wsparcie &4&l<3 "));
            Bukkit.broadcastMessage(ChatUtil.fixColor("&fNasz ItemShop&8: &f&lWWW.ARGANIA.PL/SKLEP"));
            Bukkit.broadcastMessage(ChatUtil.fixColor("&8----------------------------------"));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "case key " + args[0] + " 5");
            return true;
        }
        return false;
    }
}
