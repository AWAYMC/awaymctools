package net.argania.core.commands;

import net.argania.core.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ItemshopCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String lab, String[] args) {
        if(cmd.getName().equalsIgnoreCase("itemshop")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage(ChatUtil.fixColor("&cNiestety, to nie jest dla konsoli!"));
                return false;
            }
            if(!(sender.hasPermission("argania.core.itemshop"))) {
                sender.sendMessage(ChatUtil.fixColor("&4Blad: &cNie masz uprawien (argania.core.itemshop"));
                return false;
            }
            if(args.length < 1) {
                sender.sendMessage(ChatUtil.fixColor("&4Blad: &cPoprawne uzycie: /itemshop <nick> <vip/unban/key>"));
                return false;
            }
            if(args.length < 2) {
                sender.sendMessage(ChatUtil.fixColor("&4Blad: &cPodaj usluge!"));
                return false;
            }
            if(args[1].equalsIgnoreCase("vip")) {
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8----------------------------------"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8>> &7Gracz &c" + args[0] + " &7zakupil range&8: &cVIP"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8>> &cDziekujemy za wsparcie &4&l<3 "));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8>> &fNasz ItemShop&8: &f&lWWW.ARGANIA.PL/SKLEP"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8----------------------------------"));
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " group set VIP");
                return true;
            }
            if(args[1].equalsIgnoreCase("unban")) {
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8----------------------------------"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8>> &7Gracz &c" + args[0] + " &7zakupil usluge&8: &cUNBAN"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8>> &cDziekujemy za wsparcie &4&l<3 "));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8>> &fNasz ItemShop&8: &f&lWWW.ARGANIA.PL/SKLEP"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8----------------------------------"));
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "unban " + args[0]);
                return true;
            }
            if(args[1].equalsIgnoreCase("key")) {
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8----------------------------------"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8>> &7Gracz &c" + args[0] + " &7zakupil usluge&8: &cKLUCZE"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8>> &cDziekujemy za wsparcie &4&l<3 "));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8>> &fNasz ItemShop&8: &f&lWWW.ARGANIA.PL/SKLEP"));
                Bukkit.broadcastMessage(ChatUtil.fixColor("&8----------------------------------"));
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "case key " + args[0] + " 5");
                return true;
            }
        }
        return false;
    }
}

